package com.xtn.modules.blog.controller;


import com.xtn.common.annotation.NoLoginNeed;
import com.xtn.common.base.Result;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 微信小程序端-博客模块
 * @author xCoder
 */
@RestController
@RequestMapping("/front/wx/blog")
@Api(value = "博客模块")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    /**
     * 测试
     */
    @NoLoginNeed
    @PostMapping("test")
    @ApiOperation("测试")
    public Result<Void> test() {
        List<Blog> list = blogService.selectAll();
        System.out.println(list.size());
        return Result.ok();
    }

}
