package tech.nexus.xnexus.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nexus.xnexus.common.BaseResponse;
import tech.nexus.xnexus.common.ResultUtils;
import tech.nexus.xnexus.model.request.user.UserLoginRequest;
import tech.nexus.xnexus.model.request.user.UserRegistryRequest;
import tech.nexus.xnexus.model.vo.user.UserLoginVo;
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
    public BaseResponse<Boolean> register(@RequestBody @Valid UserRegistryRequest userRegistryRequest) {

        return ResultUtils.success(userService.register(userRegistryRequest));
    }

    @PostMapping("login")
    public BaseResponse<UserLoginVo> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return ResultUtils.success(userService.login(userLoginRequest));

    }

}
