
package com.xtn.common.config;

import com.xtn.common.annotation.LoginUser;
import com.xtn.common.utils.EnvironmentUtil;
import com.xtn.common.utils.SpringContextUtil;
import com.xtn.modules.system.entity.SysUser;
import com.xtn.modules.system.service.SysUserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author xCoder
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(SysUser.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) {
        /*if (EnvironmentUtil.isDefault()) {
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUsername("管理员");
            return user;
        }*/

        //获取用户ID
        Object object = request.getAttribute(FrontApplicationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if(object == null){
            return null;
        }

        SysUserService sysUserService = SpringContextUtil.getBean(SysUserService.class);
        //获取用户信息
        return sysUserService.getById((Long)object);
    }
}
