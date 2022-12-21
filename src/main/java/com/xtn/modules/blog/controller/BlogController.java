package com.xtn.modules.blog.controller;


import com.github.pagehelper.PageInfo;
import com.xtn.common.annotation.LoginUser;
import com.xtn.common.annotation.NoLoginNeed;
import com.xtn.common.base.Paging;
import com.xtn.common.base.Result;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.request.BlogSaveRequest;
import com.xtn.modules.blog.service.BlogService;
import com.xtn.modules.blog.vo.BlogListVo;
import com.xtn.modules.system.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
     * 博客列表
     * @param type 类型（1：公开，2：我的）
     * @param paging 分页参数
     * @param sysUser 登录用户
     */
    @GetMapping("list")
    @ApiOperation("博客列表")
    @ApiImplicitParam(name = "type", value = "类型（1：公开，2：我的）", required = true)
    public Result<PageInfo<BlogListVo>> list(@RequestParam Integer type,
                                             @RequestParam Paging paging,
                                             @LoginUser SysUser sysUser) {
        PageInfo<BlogListVo> pageInfo = blogService.getBlogList(type, paging, sysUser);
        return Result.ok(pageInfo);
    }

    /**
     * 新增博客
     * @param request 请求参数
     * @param sysUser 登录用户
     */
    @GetMapping("save")
    @ApiOperation("新增博客")
    public Result<Void> save(@RequestBody @Valid BlogSaveRequest request, @LoginUser SysUser sysUser) {
        blogService.saveBlog(request, sysUser);
        return Result.ok();
    }

}
