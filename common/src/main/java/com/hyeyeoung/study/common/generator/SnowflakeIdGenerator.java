package com.hyeyeoung.study.common.generator;

/**
 * 트위터 Snowflake 공식 Git 주소: <a href="https://github.com/twitter-archive/snowflake/pull/27/files">...</a>
 */
public class SnowflakeIdGenerator {

    // Snowflake 알고리즘에서 사용되는 시작 시간(epoch). 2010년 11월 4일 01:42:54 기준으로 함
    // 원하는 값으로 변경하여 사용
    private final long twepoch = 1288834974657L;

    // 워커 ID를 표현하는 비트 수
    private final long workerIdBits = 10L;

    // 워커 ID로 표현할 수 있는 최대값
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    // 시퀀스 번호를 표현하는 비트 수
    private final long sequenceBits = 12L;

    // 워커 ID를 시퀀스 번호 왼쪽으로 이동시키는 비트 수
    private final long workerIdLeftShift = sequenceBits;

    // 타임스탬프를 시퀀스 번호와 워커 ID 왼쪽으로 이동시키는 비트 수
    private final long timestampLeftShift = sequenceBits + workerIdBits;

    // 시퀀스 번호를 생성할 때 사용하는 비트 수에 대한 마스크
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId; // 현재 워커 ID
    private long sequence = 0L; // 현재 시퀀스 번호
    private long lastTimestamp = -1L; // 마지막으로 생성된 ID의 타임스탬프

    // SnowflakeIdGenerator 생성자
    public SnowflakeIdGenerator(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker ID는 0보다 작거나 %d보다 크거나 같아야 합니다.", maxWorkerId));
        }
        this.workerId = workerId;
    }

    // 다음 Snowflake ID를 생성하는 메서드
    public synchronized long nextId() {
        long timestamp = timeGen(); // 현재 시간을 얻어옴

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("시간이 되돌아갔습니다.");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask; // 시퀀스 번호를 증가시킴
            if (sequence == 0) {
                timestamp = untilNextMillis(lastTimestamp); // 다음 밀리초까지 대기함
            }
        } else {
            sequence = 0L; // 새로운 타임스탬프이므로 시퀀스 번호를 초기화함
        }

        lastTimestamp = timestamp; // 마지막 타임스탬프를 업데이트함

        // Snowflake ID를 생성하여 반환함
        return ((timestamp - twepoch) << timestampLeftShift) |
                (workerId << workerIdLeftShift) |
                sequence;
    }

    // 다음 밀리초까지 대기하는 메서드
    private long untilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    // 현재 시간을 반환하는 메서드
    private long timeGen() {
        return System.currentTimeMillis();
    }
}