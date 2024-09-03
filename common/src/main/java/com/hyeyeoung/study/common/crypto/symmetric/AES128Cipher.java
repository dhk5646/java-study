package com.hyeyeoung.study.common.crypto.symmetric;

import com.hyeyeoung.study.common.crypto.symmetric.enums.AESKeySize;

public class AES128Cipher extends AESCipher {

    public AES128Cipher(byte[] keyBytes) throws Exception {
        super(AESKeySize._128, keyBytes);
    }
}
