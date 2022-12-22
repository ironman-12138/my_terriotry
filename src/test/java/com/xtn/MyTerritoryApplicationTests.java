package com.xtn;

import com.xtn.common.utils.BeanCopyUtil;
import com.xtn.modules.blog.entity.Blog;
import com.xtn.modules.blog.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MyTerritoryApplicationTests {

    @Resource
    private BlogMapper blogMapper;

    @Test
    void test1() {

        Blog blog = new Blog();
        blog.setId(2L);
        blog.setTitle("666");
        blog.setContent(null);
        blogMapper.updateById(blog);
    }

}
