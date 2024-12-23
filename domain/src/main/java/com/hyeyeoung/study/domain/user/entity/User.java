package com.hyeyeoung.study.domain.user.entity;

import com.hyeyeoung.study.common.constants.TableConstants;
import com.hyeyeoung.study.domain.common.entity.BaseEntity;
import com.hyeyeoung.study.domain.user.enums.UserRoleEnum;
import com.hyeyeoung.study.domain.user.enums.UserStatusEnum;
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
public class User extends BaseEntity {

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
    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatusEnum;

    @Column
    private String lastLoginIp;

    @Column
    private LocalDateTime lastLoginDateTime;

    @Column
    private LocalDateTime lastPasswordChangeDateTime;

    @Column
    private Integer failedLoginAttempts; // 로그인 실패 횟수

    public void login() {
        this.lastLoginDateTime = LocalDateTime.now();
        this.failedLoginAttempts = 0;
        this.lastLoginIp = "127.0.0.1"; // TODO: 수정하자.
    }

    public void incrementFailedLoginAttempts() {
        this.failedLoginAttempts++;
    }
}
