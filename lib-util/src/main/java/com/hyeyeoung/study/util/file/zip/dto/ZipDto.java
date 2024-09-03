package com.hyeyeoung.study.util.file.zip.dto;

import java.io.InputStream;

public record ZipDto(String fileName, InputStream inputStream) {
    public static ZipDto create(String fileName, InputStream inputStream) {
        return new ZipDto(fileName, inputStream);
    }

}
