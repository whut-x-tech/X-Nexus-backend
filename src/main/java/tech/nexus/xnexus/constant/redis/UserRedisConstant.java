package tech.nexus.xnexus.constant.redis;

/**
 * 用户相关业务使用到 redis 时一些 key 的前缀或者特定的过期时间常量 {@link tech.nexus.xnexus.service.impl.UserServiceImpl}
 * @author liuqiao
 * @since 2025-04-09
 */
public interface UserRedisConstant {

    String REGISTRY_LOCK_PREFIX = "user:register:";

    long REGISTRY_LOCK_TTL = 5_000L;
}
