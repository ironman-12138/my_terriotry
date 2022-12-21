package com.xtn.modules.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.vo.BlogListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 博客
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 博客列表
     * @param type 类型（1：公开，2：我的）
     * @param id 登录用户id
     */
    List<BlogListVo> getBlogList(@Param("type") Integer type, @Param("id") Long id);
}