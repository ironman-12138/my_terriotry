package com.xtn.modules.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtn.modules.blog.entity.BlogLabel;
import com.xtn.modules.blog.request.BlogLabelRequest;

/**
 * 博客标签接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface BlogLabelService extends IService<BlogLabel> {

    /**
     * 新增博客标签
     * @param request 请求参数
     */
    void saveBlogLabel(BlogLabelRequest request);

    /**
     * 编辑博客标签
     * @param request 请求参数
     */
    void updateBlogLabel(BlogLabelRequest request);

    /**
     * 删除博客标签
     * @param id 请求参数
     */
    void deleteBlogLabel(Long id);
}