package com.xtn.modules.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.system.entity.SysUser;

import java.util.List;

/**
 * 博客接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface BlogService extends IService<Blog> {

    List<Blog> selectAll();
}