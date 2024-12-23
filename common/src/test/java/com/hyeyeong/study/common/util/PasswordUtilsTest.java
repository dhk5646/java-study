package com.hyeyeong.study.common.util;

import com.hyeyeoung.study.common.util.PasswordUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordUtilsTest {

    @Test
    @DisplayName("같은 평문비밀번호를 암호화한 결과는 틀려야한다.")
    public void testCase1() {

        // given
        String password = "admin";

        // when
        String hashPassword = PasswordUtils.hashPassword(password);
        String hashPassword2 = PasswordUtils.hashPassword(password);

        // then
        Assertions.assertNotEquals(hashPassword, hashPassword2);
    }

    @Test
    @DisplayName("평문비밀번호와 암호화비밀번호 비교 테스트")
    public void testCase2() {
        // given
        String password = "admin";
        String hashPassword = PasswordUtils.hashPassword(password);

        // when
        boolean isValid = PasswordUtils.checkPassword(password, hashPassword);

        // then
        Assertions.assertTrue(isValid);
    }
}
