package com.xtn.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xCoder
 */
@Slf4j
@Component
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("项目启动成功，接口文档地址：http://localhost:8081/myTerritory/doc.html");
    }
}
