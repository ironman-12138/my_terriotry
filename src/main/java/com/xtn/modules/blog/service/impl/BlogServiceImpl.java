package com.xtn.modules.blog.service.impl;

import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.mapper.BlogMapper;
import com.xtn.modules.blog.service.BlogService;
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
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;


    @Override
    public List<Blog> selectAll() {
        return blogMapper.selectAll();
    }
}