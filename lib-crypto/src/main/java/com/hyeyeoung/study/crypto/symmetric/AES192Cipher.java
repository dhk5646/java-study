package com.hyeyeoung.study.crypto.symmetric;

import com.hyeyeoung.study.crypto.symmetric.enums.AESKeySize;

public class AES192Cipher extends AESCipher {

    public AES192Cipher(String keyString) throws Exception {
        super(AESKeySize._192, keyString);
    }
}
