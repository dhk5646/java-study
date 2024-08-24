package com.hyeyeoung.study.domain.sample.entity;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Sample implements Serializable {
    private String column1;
    private String column2;

    public static Sample of(String column1, String column2) {
        return new Sample(column1, column2);
    }

    public static Sample empty() {
        return new Sample();
    }
}
