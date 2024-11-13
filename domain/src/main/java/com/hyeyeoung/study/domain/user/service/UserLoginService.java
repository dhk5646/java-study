package com.hyeyeoung.study.domain.user.service;

import com.hyeyeoung.study.common.jwt.JwtGenerator;
import com.hyeyeoung.study.common.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.common.response.exception.ApiResponseException;
import com.hyeyeoung.study.domain.user.dto.param.UserLoginParam;
import com.hyeyeoung.study.domain.user.dto.result.UserLoginResult;
import com.hyeyeoung.study.domain.user.entity.User;
import com.hyeyeoung.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLoginService {

    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;

    public UserLoginResult login(UserLoginParam param) {
        Optional<User> optionalUser = userRepository.findByIdAndPassword(param.getId(), param.getPassword());

        if (optionalUser.isEmpty()) throw new ApiResponseException(ApiResponseEnum.NOT_EXIST);

        User user = optionalUser.get();

        // JWT 토큰 발행
        String token = jwtGenerator.generate(user.getUserSeq().toString());

        user.login();

        return UserLoginResult.of(user, token);
    }


}
