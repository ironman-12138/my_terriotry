package com.xtn.modules.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xtn.common.base.Paging;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.request.BlogSaveRequest;
import com.xtn.modules.blog.vo.BlogListVo;
import com.xtn.modules.system.entity.SysUser;

import java.util.List;

/**
 * 博客接口
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
public interface BlogService extends IService<Blog> {

    /**
     * 博客列表
     * @param type 类型（1：公开，2：我的）
     * @param paging 分页参数
     * @param sysUser 登录用户
     */
    PageInfo<BlogListVo> getBlogList(Integer type, Paging paging, SysUser sysUser);

    /**
     * 新增博客
     * @param request 请求参数
     * @param sysUser 登录用户
     */
    void saveBlog(BlogSaveRequest request, SysUser sysUser);
}