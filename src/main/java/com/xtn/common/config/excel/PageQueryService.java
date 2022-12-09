package com.xtn.common.config.excel;

import java.util.List;

/**
 * 导出查询接口
 * @author xCoder
 */
@FunctionalInterface
public interface PageQueryService<E> {

    /**
     * 获取分页查询数据
     * @param pageIndex 当前页，从1开始
     * @param pageSize 每页查询数量
     */
    List<E> pageList(int pageIndex, int pageSize);

}
