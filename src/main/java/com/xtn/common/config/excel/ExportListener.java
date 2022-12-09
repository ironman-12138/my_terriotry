package com.xtn.common.config.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * excel导出
 * @author xCoder
 */
@Slf4j
public class ExportListener<E> {

    private static final String DATA_FORMAT = "yyyy-MM-dd-HH-mm-ss";

    private static final String CHARACTER_UTF_8 = "UTF-8";

    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    private static final String CACHE_CONTROL = "Cache-Control";

    private static final String NO_STORE = "no-store";

    private static final String MAX_AGE = "max-age=0";

    private static final Integer PAGE_SIZE = 1000;

    /**
     * excel导出
     * @param response 响应流
     * @param sheetName 文件名
     * @param pojoClass 导出对象
     * @param totalCount 最大导出条数
     * @param pageQueryService 分页接口
     */
    public void exportExcel(HttpServletResponse response, String sheetName, Class<E> pojoClass, int totalCount, PageQueryService<E> pageQueryService) {
        ServletOutputStream out = null;
        ExcelWriter excelWriter = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_FORMAT);
            String nowTime = formatter.format(LocalDateTime.now());

            response.setContentType(CONTENT_TYPE);
            //设置字符集为utf-8
            response.setCharacterEncoding(CHARACTER_UTF_8);

            String fileName = sheetName.concat("-").concat(nowTime).concat(".xlsx");

            response.setHeader(CONTENT_DISPOSITION,
                    "attachment;filename=" + URLEncoder.encode(fileName, CHARACTER_UTF_8)
                            + ";filename*=utf-8''" + URLEncoder.encode(fileName, CHARACTER_UTF_8));

            //发送一个报头，告诉浏览器当前页面不进行缓存，每次访问的时间必须从服务器上读取最新的数据
            response.setHeader(CACHE_CONTROL, NO_STORE);
            response.addHeader(CACHE_CONTROL, MAX_AGE);

            //指定导出的class类
            out = response.getOutputStream();
            excelWriter = EasyExcel.write(out, pojoClass).build();
            //如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();

            //计算需要多少页
            int pageNumber = (int) Math.ceil((double) totalCount / (double) PAGE_SIZE);

            List<E> pageList = new ArrayList<>();

            // 分页获取数据
            for (int i = 1; i <= pageNumber; i++) {
                pageList = pageQueryService.pageList(i, PAGE_SIZE);
                if (pageList.size() == 0) {
                    break;
                }
                excelWriter.write(pageList , writeSheet);
                pageList.clear();
            }
            excelWriter.finish();
            out.flush();
        } catch (IOException e) {
            log.error("导出异常：{}", e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
