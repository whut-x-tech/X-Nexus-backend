package tech.nexus.xnexus.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import tech.nexus.xnexus.common.ErrorCode;
import tech.nexus.xnexus.exception.ThrowUtils;
import tech.nexus.xnexus.util.UserHolder;

/**
 * 检查用户是否登录成功 {@link UserHolder} 获取缓存登录对象
 *
 * @author liuqiao
 * @since 2025-04-10
 */
public class LoginSuccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThrowUtils.throwIf(UserHolder.get() == null, ErrorCode.NOT_LOGIN_ERROR, "没有登录");
        return true;
    }
}
