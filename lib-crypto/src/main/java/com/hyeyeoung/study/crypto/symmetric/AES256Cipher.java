package com.hyeyeoung.study.crypto.symmetric;

import com.hyeyeoung.study.crypto.symmetric.enums.AESKeySize;

public class AES256Cipher extends AESCipher {

    public AES256Cipher(byte[] keyBytes) throws Exception {
        super(AESKeySize._256, keyBytes);
    }
}
