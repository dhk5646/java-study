package com.hyeyeoung.study.appapi.domain.user.controller;

import com.hyeyeoung.study.appapi.domain.user.dto.request.UserLoginRequest;
import com.hyeyeoung.study.appapi.domain.user.dto.response.UserLoginResponse;
import com.hyeyeoung.study.common.response.ApiResponse;
import com.hyeyeoung.study.domain.user.dto.result.UserLoginResult;
import com.hyeyeoung.study.domain.user.service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserLoginService userLoginService;

    @GetMapping("/login")
    public ApiResponse<UserLoginResponse> loginUser(@Valid UserLoginRequest request) {
        UserLoginResult result = userLoginService.login(request.toUserLoginParam());
        return ApiResponse.success(UserLoginResponse.from(result));
    }

}
