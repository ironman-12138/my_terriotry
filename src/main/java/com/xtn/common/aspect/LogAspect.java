package com.xtn.common.aspect;

import com.xtn.common.base.Result;
import com.xtn.common.base.ResultCode;
import com.xtn.common.utils.HttpUtil;
import com.xtn.common.utils.JsonUtil;
import com.xtn.common.utils.TLocalHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 86138
 */
@Order(1)
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * 用于记录请求时间
     */
    ThreadLocal<Long> start = new ThreadLocal<>();


    private String getLogUserName() {

        return "未登录";
    }
    /**
     * 记录接口日志
     *
     * @param proceeding
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Around(value = "execution(* com.xtn..controller..*.*(..))")
    public Object log(ProceedingJoinPoint proceeding) throws Throwable {
        start.set(System.currentTimeMillis());
        HttpServletRequest request = HttpUtil.getRequest();
        Signature signature = proceeding.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //请求参数
        String requestParam = JsonUtil.toJsonString(AllLogAspect.getParameter(method, proceeding.getArgs()));
        log.info("请求路径:{},请求参数:{},流水号:{}", request.getRequestURI(), requestParam, TLocalHelper.getSeq());
        // 获取用户名
        String username = getLogUserName();
        Object result = proceeding.proceed();
        long time = System.currentTimeMillis() - start.get();
        // 如果返回的结果是RestResponse, 并且code值不是00(success), 则记录为异常日志
        if (result instanceof Result) {
            Result res = (Result) result;
            if (!ResultCode.SUCCESS_CODE.equals(res.getCode())) {
                log.info("方法请求成功,返回code值:{},error:{}", res.getCode(), res.getMsg());
            } else {
                log.info("方法请求失败,返回code值:{},success:{}", res.getCode(), res.getMsg());
            }
            // 记录日志
            AllLogAspect.addLog(request, proceeding, res.getCode(), res.getMsg(), username, requestParam, TLocalHelper.getSeq(), time);
        } else {
            // 其他情况记录异常日志
            AllLogAspect.addLog(request, proceeding, ResultCode.UN_KNOW_ERROR, ResultCode.UN_KNOW_ERROR_DESC, username, requestParam, TLocalHelper.getSeq(), time);
        }
        log.info("请求结束,耗时:{}ms", time);

        return result;
    }
}
