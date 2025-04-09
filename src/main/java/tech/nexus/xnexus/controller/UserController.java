package tech.nexus.xnexus.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nexus.xnexus.common.BaseResponse;
import tech.nexus.xnexus.common.ResultUtils;
import tech.nexus.xnexus.mapper.UserMapper;
import tech.nexus.xnexus.model.request.UserRegistryRequest;
import tech.nexus.xnexus.service.UserService;

/**
 * 处理用户相关请求控制器
 *
 * @author liuqiao
 * @since 2025-04-09
 */
@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public BaseResponse<Boolean> register(@RequestBody @Validated UserRegistryRequest userRegistryRequest) {

        return ResultUtils.success(userService.register(userRegistryRequest));
    }

}
