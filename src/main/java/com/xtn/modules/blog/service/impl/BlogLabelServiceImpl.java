package com.xtn.modules.blog.service.impl;

import com.xtn.modules.blog.mapper.BlogLabelMapper;
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
public class BlogLabelServiceImpl implements BlogLabelService {

    @Resource
    private BlogLabelMapper blogLabelMapper;


}