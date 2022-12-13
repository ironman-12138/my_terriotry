package com.xtn.modules.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 博客
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    List<Blog> selectAll();
}