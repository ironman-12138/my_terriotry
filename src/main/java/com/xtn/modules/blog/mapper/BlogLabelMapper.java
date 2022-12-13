package com.xtn.modules.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtn.modules.blog.entity.BlogLabel;
import org.apache.ibatis.annotations.Mapper;


/**
 * 博客标签
 *
 * @author xCoder
 * @since 1.0.0 2022-12-13
 */
@Mapper
public interface BlogLabelMapper extends BaseMapper<BlogLabel> {
	
}