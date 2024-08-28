package com.hyeyeoung.study.crypto.symmetric;

import com.hyeyeoung.study.crypto.symmetric.enums.AESKeySize;

public class AES128Cipher extends AESCipher {

    public AES128Cipher(String keyString) throws Exception {
        super(AESKeySize._128, keyString);
    }
}
