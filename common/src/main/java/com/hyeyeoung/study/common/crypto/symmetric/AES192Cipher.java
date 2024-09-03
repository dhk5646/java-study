package com.hyeyeoung.study.common.crypto.symmetric;

import com.hyeyeoung.study.common.crypto.symmetric.enums.AESKeySize;

public class AES192Cipher extends AESCipher {

    public AES192Cipher(byte[] keyBytes) throws Exception {
        super(AESKeySize._192, keyBytes);
    }
}
