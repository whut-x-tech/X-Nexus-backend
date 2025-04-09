package tech.nexus.xnexus.service;

import tech.nexus.xnexus.model.request.UserRegistryRequest;

/**
 * @author liuqiao
 * @since 2025-04-09
 */
public interface UserService {

    /**
     * 处理用户注册
     * @param userRegistryRequest 带有用户帐号和密码
     * @return 注册成功 true   注册失败 false
     */
    Boolean register(UserRegistryRequest userRegistryRequest);
}
