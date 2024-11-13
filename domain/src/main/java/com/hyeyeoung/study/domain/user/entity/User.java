package com.hyeyeoung.study.domain.user.entity;

import com.hyeyeoung.study.common.constants.TableConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TableConstants.USER)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSeq;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String mobileNumber; // 핸드폰 번호

    @Column
    private LocalDateTime lastLoginDateTime;

    public void login() {
        this.lastLoginDateTime = LocalDateTime.now();
    }
}
