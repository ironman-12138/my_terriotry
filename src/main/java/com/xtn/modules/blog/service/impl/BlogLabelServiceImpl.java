package com.xtn.modules.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xtn.common.base.DeleteAt;
import com.xtn.common.utils.BeanCopyUtil;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.entity.BlogLabel;
import com.xtn.modules.blog.mapper.BlogLabelMapper;
import com.xtn.modules.blog.request.BlogLabelRequest;
import com.xtn.modules.blog.service.BlogLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 博客标签接口实现类
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Service
public class BlogLabelServiceImpl extends ServiceImpl<BlogLabelMapper, BlogLabel> implements BlogLabelService {

    @Resource
    private BlogLabelMapper blogLabelMapper;


    @Override
    public void saveBlogLabel(BlogLabelRequest request) {
        BlogLabel blogLabel = new BlogLabel();
        BeanCopyUtil.copy(request, blogLabel);
        blogLabelMapper.insert(blogLabel);
    }

    @Override
    public void updateBlogLabel(BlogLabelRequest request) {
        BlogLabel blogLabel = new BlogLabel();
        BeanCopyUtil.copy(request, blogLabel);
        blogLabelMapper.updateById(blogLabel);
    }

    @Override
    public void deleteBlogLabel(Long id) {
        BlogLabel blogLabel = new BlogLabel();
        blogLabel.setId(id);
        blogLabel.setDeleteAt(DeleteAt.DELETE);
        blogLabelMapper.updateById(blogLabel);
    }
}