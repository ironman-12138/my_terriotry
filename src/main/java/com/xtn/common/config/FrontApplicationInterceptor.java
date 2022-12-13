package com.xtn.common.config;

import com.xtn.common.annotation.NoLoginNeed;
import com.xtn.common.base.ResultCode;
import com.xtn.common.exception.BusinessException;
import com.xtn.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FrontApplicationInterceptor extends HandlerInterceptorAdapter {

    JwtUtils jwtUtils;

    public FrontApplicationInterceptor(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoLoginNeed noLoginNeed = handlerMethod.getMethodAnnotation(NoLoginNeed.class);
            // 标记为不需要登录的不校验
            if (noLoginNeed != null && noLoginNeed.value()) {
                return true;
            }
        }

        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());

        //凭证为空
        if(!StringUtils.hasText(token)){
            throw new BusinessException(ResultCode.LOGIN_ERROR, jwtUtils.getHeader() + "不能为空");
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
            throw new BusinessException(ResultCode.LOGIN_ERROR, jwtUtils.getHeader() + "失效，请重新登录");
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, Long.parseLong(claims.getSubject()));

        return true;
    }
}
