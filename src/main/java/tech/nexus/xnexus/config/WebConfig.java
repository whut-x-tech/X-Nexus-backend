package tech.nexus.xnexus.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.nexus.xnexus.interceptor.JwtResolveInterceptor;

/**
 * web mvc 相关配置
 * @author liuqiao
 * @since 2025-04-10
 */
@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringRedisTemplate redisTemplate;

    /**
     * 添加自定义拦截器 {@link org.springframework.web.servlet.HandlerInterceptor}
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtResolveInterceptor(redisTemplate));
    }
}
