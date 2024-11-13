package com.hyeyeoung.study.appapi.domain.user.dto.request;

import com.hyeyeoung.study.domain.user.dto.param.UserLoginParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String id;
    private String password;

    public UserLoginParam toUserLoginParam() {
        return UserLoginParam.of(id, password);
    }
}
