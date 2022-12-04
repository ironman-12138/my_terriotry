package com.xtn.common.base.db;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.xtn.common.utils.TimeUtil;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author 86138
 */
public class IdValueTableNameHandler implements TableNameHandler {

    private static final ThreadLocal<String> YEAR_LOCAL = new ThreadLocal<>();
    //使用ThreadLocal防止多线程相互影响
    private static final ThreadLocal<String> ID_VALUE = new ThreadLocal<>();

    private static final Pattern PATTERN = Pattern.compile("\\d*");

    public static void setYearAndId(String setYear, String id) {
        YEAR_LOCAL.set(setYear);
        ID_VALUE.set(id);
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        String t = getTableName(tableName, YEAR_LOCAL.get(), ID_VALUE.get());
        //这里清除ThreadLocal的值，防止线程复用出现问题
        ID_VALUE.set(null);
        YEAR_LOCAL.set(null);
        return t;
    }

    /**
     * @param tableName
     * @param year
     * @param idValue
     * @return
     */
    public static String getTableName(String tableName, String year, String idValue) {

        Assert.isTrue(Objects.nonNull(idValue), "请设置id值");
        //取余分表
        String suffix = String.valueOf(Long.parseLong(idValue) % 10);

        String tableYear = !StringUtils.hasText(year) ? TimeUtil.currentYear() : year;
        return tableName + "_" + tableYear + "_" + suffix;
    }

}
