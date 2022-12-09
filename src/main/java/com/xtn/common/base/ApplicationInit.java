package com.xtn.common.base;

import com.xtn.common.base.db.TableProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 程序启动后，立即执行的方法
 *
 * @author 86138
 */
@Slf4j
@Component
public class ApplicationInit implements ApplicationRunner {

    @Autowired(required = false)
    private Map<String, TableProcessor> tableHandler = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) {
        // 项目启动完成，初始化数据库表
        checkTableExist();
    }

    private void checkTableExist() {
        log.info("项目启动完成，开始检查分表是否存在");
        for (String key : tableHandler.keySet()) {
            TableProcessor tableProcessor = tableHandler.get(key);
            tableProcessor.checkTable();
        }
        log.info("检查分表存在-结束");
    }
}
