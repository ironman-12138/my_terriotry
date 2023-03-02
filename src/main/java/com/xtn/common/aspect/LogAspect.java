package com.xtn.common.aspect;

import com.xtn.common.base.Result;
import com.xtn.common.base.ResultCode;
import com.xtn.common.config.FrontApplicationInterceptor;
import com.xtn.common.utils.HttpUtil;
import com.xtn.common.utils.JsonUtil;
import com.xtn.common.utils.SpringContextUtil;
import com.xtn.common.utils.TLocalHelper;
import com.xtn.modules.system.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

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
        Object result = proceeding.proceed();
        long time = System.currentTimeMillis() - start.get();
        // 如果返回的结果是RestResponse, 并且code值不是00(success), 则记录为异常日志
        if (result instanceof Result) {
            Result res = (Result) result;
            // 记录日志
            addLog(request, proceeding, res.getCode(), res.getMsg(), requestParam, TLocalHelper.getSeq(), time);
        } else {
            // 其他情况记录异常日志
            addLog(request, proceeding, ResultCode.UN_KNOW_ERROR, ResultCode.UN_KNOW_ERROR_DESC, requestParam, TLocalHelper.getSeq(), time);
        }

        return result;
    }

    /**
     * 新增日志
     * @param request 请求
     * @param joinPoint 入参
     * @param status 状态值
     * @param errorMsg 错误信息
     * @param requestParam 请求参数
     * @param localSeq 流水号
     * @param time 耗时
     */
    public static void addLog(HttpServletRequest request, JoinPoint joinPoint, String status, String errorMsg, String requestParam, String localSeq, Long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取用户ID
        Object attribute = request.getAttribute(FrontApplicationInterceptor.USER_NAME);
        String username = Objects.nonNull(attribute) ? String.valueOf(attribute) : "未登录";

        String methodName = "";
        // 获取Log注解中的操作内容
        ApiOperation apiLog = method.getAnnotation(ApiOperation.class);
        if(apiLog != null) {
            apiLog.value();
            if (apiLog.value().length() > 0) {
                methodName = apiLog.value();
            }
        }
        //log.info("新增日志---->接口名称：{}，状态值：{}，状态信息：{}，调用者：{}，请求参数：{}，流水号：{}，耗时：{}ms", methodName, status, errorMsg, username, requestParam, localSeq, time);
        SysLogService sysLogService = SpringContextUtil.getBean(SysLogService.class);
        sysLogService.saveLog(methodName, status, errorMsg, username, requestParam, localSeq, time);
    }
}
