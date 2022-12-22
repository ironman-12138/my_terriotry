package com.xtn.modules.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xtn.common.base.DeleteAt;
import com.xtn.common.base.Result;
import com.xtn.modules.blog.entity.BlogLabel;
import com.xtn.modules.blog.request.BlogLabelRequest;
import com.xtn.modules.blog.service.BlogLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客标签管理
 * @author xCoder
 */
@RestController
@RequestMapping("/blogLabel")
@Api(tags = "博客标签管理")
@AllArgsConstructor
public class BlogLabelController {

    private final BlogLabelService blogLabelService;

    /**
     * 标签列表
     */
    @GetMapping("list")
    @ApiOperation("标签列表")
    public Result<List<BlogLabel>> list() {
        List<BlogLabel> list = blogLabelService.list(new QueryWrapper<BlogLabel>().eq("delete_at", DeleteAt.NOT_DELETE));
        return Result.ok(list);
    }

    /**
     * 新增博客标签
     * @param request 请求参数
     */
    @PostMapping("save")
    @ApiOperation("新增博客标签")
    public Result<Void> save(@RequestBody @Validated(BlogLabelRequest.Add.class) BlogLabelRequest request) {
        blogLabelService.saveBlogLabel(request);
        return Result.ok();
    }

    /**
     * 编辑博客标签
     * @param request 请求参数
     */
    @PostMapping("update")
    @ApiOperation("编辑博客标签")
    public Result<Void> update(@RequestBody @Validated(BlogLabelRequest.Update.class) BlogLabelRequest request) {
        blogLabelService.updateBlogLabel(request);
        return Result.ok();
    }

    /**
     * 删除博客标签
     * @param id 请求参数
     */
    @PostMapping("delete")
    @ApiOperation("删除博客标签")
    @ApiImplicitParam(name = "id", required = true, value = "标签id", paramType = "query")
    public Result<Void> delete(@RequestParam Long id) {
        blogLabelService.deleteBlogLabel(id);
        return Result.ok();
    }

}
