package com.xtn.modules.blog.controller;


import com.github.pagehelper.PageInfo;
import com.xtn.common.annotation.LoginUser;
import com.xtn.common.base.Paging;
import com.xtn.common.base.Result;
import com.xtn.modules.blog.request.BlogRequest;
import com.xtn.modules.blog.service.BlogService;
import com.xtn.modules.blog.vo.BlogListVo;
import com.xtn.modules.system.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 微信小程序端-博客模块
 * @author xCoder
 */
@RestController
@RequestMapping("/front/wx/blog")
@Api(tags = "博客模块")
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
    @ApiImplicitParam(name = "type", required = true, value = "类型（1：公开，2：我的）", paramType = "query")
    public Result<PageInfo<BlogListVo>> list(Integer type,
                                             Paging paging,
                                             @LoginUser SysUser sysUser) {
        PageInfo<BlogListVo> pageInfo = blogService.getBlogList(type, paging, sysUser);
        return Result.ok(pageInfo);
    }

    /**
     * 新增博客
     * @param request 请求参数
     * @param sysUser 登录用户
     */
    @PostMapping("save")
    @ApiOperation("新增博客")
    public Result<Void> save(@RequestBody @Validated(BlogRequest.Add.class) BlogRequest request, @LoginUser SysUser sysUser) {
        blogService.saveBlog(request, sysUser);
        return Result.ok();
    }

    /**
     * 编辑博客
     * @param request 请求参数
     */
    @PostMapping("update")
    @ApiOperation("编辑博客")
    public Result<Void> update(@RequestBody @Validated(BlogRequest.Update.class) BlogRequest request) {
        blogService.updateBlog(request);
        return Result.ok();
    }

    /**
     * 删除博客
     * @param id 博客id
     */
    @PostMapping("delete")
    @ApiOperation("删除博客")
    @ApiImplicitParam(name = "id", required = true, value = "博客id", paramType = "query")
    public Result<Void> delete(@RequestParam Long id) {
        blogService.delete(id);
        return Result.ok();
    }

}
