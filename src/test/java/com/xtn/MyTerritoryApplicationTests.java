package com.xtn;

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
    }

}
