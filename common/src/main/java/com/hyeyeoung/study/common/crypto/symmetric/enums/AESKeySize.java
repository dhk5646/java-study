package com.hyeyeoung.study.common.crypto.symmetric.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AESKeySize {
    _128("AES128", 128, 16),
    _192("AES192", 192, 24),
    _256("AES256", 256, 32),
    ;

    private final String name;
    private final int bitSize;
    private final int byteSize; // 1byte = 8비트 = (1bit/8)

}
