package com.hyeyeoung.study.crypto.symmetric;

import com.hyeyeoung.study.crypto.symmetric.enums.AESKeySize;

public class AES256Cipher extends AESCipher {

    public AES256Cipher(String keyString) throws Exception {
        super(AESKeySize._256, keyString);
    }
}
