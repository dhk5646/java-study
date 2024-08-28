package com.hyeyeoung.study.crypto.symmetric.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AESKeySize {
    _128(16),
    _192(24),
    _256(32),
    ;

    private final int byteSize;

}
