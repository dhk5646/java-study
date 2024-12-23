package com.hyeyeoung.study.domain.user.service;

import com.hyeyeoung.study.common.jwt.JwtGenerator;
import com.hyeyeoung.study.common.response.enums.ApiResponseEnum;
import com.hyeyeoung.study.common.response.exception.ApiResponseException;
import com.hyeyeoung.study.common.util.PasswordUtils;
import com.hyeyeoung.study.domain.user.dto.param.UserLoginParam;
import com.hyeyeoung.study.domain.user.dto.result.UserLoginResult;
import com.hyeyeoung.study.domain.user.entity.User;
import com.hyeyeoung.study.domain.user.enums.UserStatusEnum;
import com.hyeyeoung.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;

    private final JwtGenerator jwtGenerator;

    @Transactional(noRollbackFor = ApiResponseException.class)
    public UserLoginResult login(UserLoginParam param) {
        // 1. 사용자 조회
        User user = fetchUserOrThrow(param.getId());

        // 2. 사용자 검증
        validateUser(user, param.getPassword());

        // 3. JWT 토큰 발행
        String token = jwtGenerator.generate(user.getUserSeq().toString());

        // 4. 사용자 로그인 상태 갱신
        user.login();

        // 5. 결과 반환
        return UserLoginResult.of(user, token);
    }

    private void validateUser(User user, String password) {
        // 1. 상태 검증
        validateStatus(user);
        // 2. 비밀번호 검증
        validatePassword(user, password);
    }

    private void validateStatus(User user) {
        if (UserStatusEnum.DELETED == user.getUserStatusEnum()) {
            throw new ApiResponseException(ApiResponseEnum.NOT_EXIST);
        }

        if (UserStatusEnum.INACTIVE == user.getUserStatusEnum()) {
            throw new ApiResponseException(ApiResponseEnum.USER_IN_ACTIVE);
        }

        if (UserStatusEnum.LOCKED == user.getUserStatusEnum()) {
            throw new ApiResponseException(ApiResponseEnum.USER_LOCKED);
        }
    }

    /**
     * 입력된 비밀번호와 저장된 비밀번호가 일치하는지 확인
     *
     * @param user     사용자
     * @param password 사용자가 입력한 비밀번호
     */
    private void validatePassword(User user, String password) {
        if (!PasswordUtils.checkPassword(password, user.getPassword())) {
            user.incrementFailedLoginAttempts();
            throw new ApiResponseException(ApiResponseEnum.UNAUTHORIZED, "Invalid password");
        }
    }

    /**
     * 사용자 조회
     *
     * @param userId 사용자 ID
     * @return 조회된 User 객체
     */
    private User fetchUserOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiResponseException(ApiResponseEnum.NOT_EXIST, "User not found: " + userId));
    }
}
