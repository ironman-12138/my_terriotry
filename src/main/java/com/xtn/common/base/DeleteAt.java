package com.xtn.common.base;

/**
 * @author xCoder
 */
public interface DeleteAt {

    /**
     * 未删除
     */
    long NOT_DELETE = 0;

    long DELETE = System.currentTimeMillis();

}
