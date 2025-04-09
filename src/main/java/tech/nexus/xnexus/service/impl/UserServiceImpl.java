package tech.nexus.xnexus.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tech.nexus.xnexus.common.ErrorCode;
import tech.nexus.xnexus.constant.redis.UserRedisConstant;
import tech.nexus.xnexus.exception.ThrowUtils;
import tech.nexus.xnexus.mapper.UserMapper;
import tech.nexus.xnexus.model.entity.User;
import tech.nexus.xnexus.model.request.UserRegistryRequest;
import tech.nexus.xnexus.service.UserService;

/**
 * @author liuqiao
 * @since 2025-04-09
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final StringRedisTemplate redisTemplate;

    private final UserMapper userMapper;

    private final RedissonClient redissonClient;

    @Override
    public Boolean register(UserRegistryRequest userRegistryRequest) {
        final String username = userRegistryRequest.getUsername();
        final String password = userRegistryRequest.getPassword();
        final String checkCode = userRegistryRequest.getCheckCode();

        // 分布式限制注册接口
        final RRateLimiter limiter = redissonClient.getRateLimiter(UserRedisConstant.REGISTRY_LOCK_PREFIX + username);
        ThrowUtils.throwIf(!limiter.trySetRate(RateType.OVERALL, 1, UserRedisConstant.REGISTRY_LOCK_TTL, RateIntervalUnit.MILLISECONDS),
                ErrorCode.FORBIDDEN_ERROR,
                "短时间不能重复注册");

        // 检查用户是否存在
        if (userMapper.isUserExist(username) == 1) {
            return Boolean.FALSE;
        }

        // 增加用户
        final User user = new User();
        // 单机环境下使用默认 worker id 1
        user.setId(IdUtil.getSnowflakeNextId());
        user.setUsername(username);
        user.setPassword(SecureUtil.md5(password));
        // todo 设置 role
        return userMapper.addUser(user);

    }
}
