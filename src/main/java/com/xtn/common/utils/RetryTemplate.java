package com.xtn.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.TimeoutException;

/**
 * 重试机制逻辑处理
 * @author xCoder
 */
@Slf4j
public abstract class RetryTemplate {

    private static final int DEFAULT_RETRY_TIME = 1;
    private int retryNum = 1;//重试次数
    private int sleepTime = 0;//重试的睡眠时间

    public int getSleepTime() {
        return sleepTime;
    }

    public RetryTemplate setSleepTime(int sleepTime) {
        if(sleepTime < 0) {
            throw new IllegalArgumentException("sleepTime should equal or bigger than 0");
        }
        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryNum() {
        return retryNum;
    }

    public RetryTemplate setRetryNum(int retryNum) {
        if (retryNum <= 0) {
            throw new IllegalArgumentException("retryNum should bigger than 0");
        }
        this.retryNum = retryNum;
        return this;
    }

    /**
     * 重试的业务执行代码
     * 失败时请抛出一个异常
     * 根据返回结果的状态来判定是否需要重试
     *
     * @return 执行结果
     */
    protected abstract Object retry() throws Throwable;

    public Object execute() throws Throwable {
        for (int i = 0; i <= retryNum; i++) {
            try {
                return retry();
            } catch (TimeoutException e) {
                log.error("业务执行出现异常，请求超时");
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                log.error("业务执行出现异常，e: {}", e.getMessage());
                Thread.sleep(sleepTime);
            }
        }
        return null;
    }

    public void submit(ThreadPoolTaskExecutor executorService) {
        if (executorService == null) {
            throw new IllegalArgumentException("please choose executorService!");
        }
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    execute();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

}
