package com.xtn.modules.blog.service;

import com.xtn.modules.blog.entity.Blog;

import java.util.List;

/**
 * 博客接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface BlogService {

    List<Blog> selectAll();
}