package com.hyeyeong.study.util.file.zip.dto;

import com.hyeyeoung.study.util.file.zip.ZipUtils;
import com.hyeyeoung.study.util.file.zip.dto.ZipDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtilsTest {

    @Test
    @DisplayName("파일2개를 Zip 파일로 압축한다.")
    public void compress_test_zip_파일이_생성되어야한다() throws IOException {

        // given
        List<String> fileNames = Arrays.asList("testFile1.txt", "testFile2.txt");
        List<ZipDto> zipDtos = fileNames.stream().map(this::createZipDto).collect(Collectors.toList());

        // when
        InputStream zipFileInputStream = ZipUtils.compress(zipDtos);

        // then
        try (ZipInputStream zipInputStream = new ZipInputStream(zipFileInputStream)) {
            ZipEntry entry;
            // 생성된 Zip 파일 안에 들어있는 파일들을 반복하면서 확인
            while ((entry = zipInputStream.getNextEntry()) != null) {
                // 파일명이 일치하는지 확인.
                Assertions.assertTrue(fileNames.contains(entry.getName()));
            }
        } catch (IOException e) {
            // Zip 파일 읽기 실패
            Assertions.fail("Failed to read Zip file: " + e.getMessage());
        }
    }

    @SneakyThrows
    private ZipDto createZipDto(String fileName) {
        try {
            String filePath = "com/hyeyeong/study/util/file/zip" + File.separator + fileName;
            InputStream inputStream = ZipUtilsTest.class.getClassLoader().getResourceAsStream(filePath);

            return ZipDto.create(fileName, inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
