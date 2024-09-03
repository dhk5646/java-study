package com.hyeyeong.study.common.generator;

import com.hyeyeoung.study.common.generator.SnowflakeIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SnowflakeIdGeneratorTest {

    @Test
    public void 고유Id가_생성되어야한다() {

        // given
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1L);

        // then
        long id = generator.nextId();

        Assertions.assertTrue(id > 0);
    }

    @Test
    public void 먼저생성한ID보다_늦게생성한ID가_더큰값이어야한다() {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1L);

        long firstId = generator.nextId();
        long secondId = generator.nextId();

        Assertions.assertTrue(firstId < secondId);
    }

    @Test
    @DisplayName("1288834974657L 이 2010-11-04 01:42:54 과 동일한지 확인")
    public void twepoch_값에_해당하는_날짜가_2021_11_04_01_42_54_와_동일해야한다() {

        // given
        long twepoch = 1288834974657L;
        LocalDateTime expected = LocalDateTime.of(2010, 11, 4, 1, 42, 54);

        // when
        LocalDateTime actual = LocalDateTime.ofEpochSecond(twepoch / 1000, 0, ZoneOffset.UTC);

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("0L 이 1900-01-01 00:00:00 과 동일한지 확인")
    public void zero_is_1900_01_01_00_00_00_이다() {

        // given
        long twepoch = 0L;
        LocalDateTime expected = LocalDateTime.of(1970, 1, 1, 0, 0, 0);

        // when
        LocalDateTime actual = LocalDateTime.ofEpochSecond(twepoch / 1000, 0, ZoneOffset.UTC);

        // then
        Assertions.assertEquals(actual, expected);
    }


    @Test
    @DisplayName("비트 연산 테스트 - 10비트의 최대 값은 1023이다.")
    public void bitOperation_10_bit_max_value_is_1023() {
        // given
        long workerIdBits = 10L;
        long expected = 1023L;
        // when
        long actual = -1L ^ (-1L << workerIdBits); // 10 비트의 max size

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("비트 연산 테스트 - 12비트 기준으로 현재 시퀀스번호가 1024이라면 다음은 0이어야 한다.")
    public void twelve_비트_기준으로_만약_현재_시퀀스번호가_최대값이라면_다음_시퀀스번호는_0_이어야한다() {
        // given
        long sequenceBits = 12L;
        long maxSequence = -1L ^ (-1L << sequenceBits);
        long sequenceMask = -1L ^ (-1L << sequenceBits);

        long expected = 0L;

        // when
        long actual = (maxSequence + 1) & sequenceMask; // 10 비트의 max size

        // then
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("bit_operation_비트왼쪽이동_테스트")
    public void bit_operation_비트왼쪽이동_테스트() {
        // given
        long workerId = 0L;
        long sequenceBits = 12L;
        long workerIdLeftShift = sequenceBits;

        long expected = 0L;

        // when
        long actual = workerId << workerIdLeftShift; // 10 비트의 max size

        // then
        Assertions.assertEquals(actual, expected);
    }
}