package com.hyeyeoung.study.crypto.symmetric;

import com.hyeyeoung.study.crypto.symmetric.enums.AESKeySize;

public class AES128Cipher extends AESCipher {

    public AES128Cipher(byte[] keyBytes) throws Exception {
        super(AESKeySize._128, keyBytes);
    }
}
