package com.hyeyeoung.study.domain.user.dto.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginParam {
    private String id;
    private String password;

    public static UserLoginParam of(String id, String password) {
        return new UserLoginParam(id, password);
    }
}
