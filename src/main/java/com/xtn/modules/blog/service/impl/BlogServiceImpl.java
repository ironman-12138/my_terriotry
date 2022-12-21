package com.xtn.modules.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtn.common.base.DeleteAt;
import com.xtn.common.base.Paging;
import com.xtn.common.utils.BeanCopyUtil;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.mapper.BlogMapper;
import com.xtn.modules.blog.request.BlogSaveRequest;
import com.xtn.modules.blog.service.BlogService;
import com.xtn.modules.blog.vo.BlogListVo;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageInfo<BlogListVo> getBlogList(Integer type, Paging paging, SysUser sysUser) {
        PageHelper.startPage(paging.getPageIndex(), paging.getPageSize());
        List<BlogListVo> list = blogMapper.getBlogList(type, sysUser.getId());
        return new PageInfo<>(list);
    }

    @Override
    public void saveBlog(BlogSaveRequest request, SysUser sysUser) {
        Blog blog = new Blog();
        BeanCopyUtil.copy(request, blog);
        blog.setCreateId(sysUser.getId());
        blog.setCreateTime(sysUser.getCreateTime());
        blog.setDeleteAt(DeleteAt.NOT_DELETE);
        blogMapper.insert(blog);
    }
}