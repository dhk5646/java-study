package com.hyeyeong.study.crypto.symmetric;


import com.hyeyeoung.study.crypto.symmetric.AES128Cipher;
import com.hyeyeoung.study.crypto.symmetric.AESCipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AESCipherTest {

    @Test
    @DisplayName("요청값과 암호화 후 복호화된 결과값은 같은값이어야한다.")
    public void testCase1() throws Exception {

        // given
        String keyString = "1234567890123456";
        String actual = "test";

        // when
        AESCipher aesCipher = new AES128Cipher(keyString.getBytes());
        String encrypted = aesCipher.encrypt(actual);
        String expected = aesCipher.decrypt(encrypted);

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("같은 키로 AESCipher 객체를 생성하더라도 암호화 결과는 달라야 한다.")
    public void testCase2() throws Exception {

        // given
        String keyString = "1234567890123456";
        String plantText = "test";

        // when
        AESCipher aesCipher1 = new AES128Cipher(keyString.getBytes());
        String encrypted1 = aesCipher1.encrypt(plantText);

        AESCipher aesCipher2 = new AES128Cipher(keyString.getBytes());
        String encrypted2 = aesCipher2.encrypt(plantText);

        // then
        Assertions.assertNotEquals(encrypted1, encrypted2);
    }
}
