package com.xtn.modules.blog.controller;


import com.xtn.common.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信小程序端-博客模块
 */
@RestController
@RequestMapping("/front/wx/blog")
@Api(value = "博客模块")
public class BlogController {

    /**
     * 测试
     */
    @PostMapping("test")
    @ApiOperation("测试")
    public Result<Void> test(){

        return Result.ok();
    }

}
