CREATE TABLE TechBlogPosts
(
    techBlogPostSeq   bigint(20) NOT NULL primary key,
    techBlogEnum      enum ('KAKAO','NAVER','WOOWAHAN') DEFAULT NULL,
    title             varchar(255)                      DEFAULT NULL,
    url               varchar(255)                      DEFAULT NULL,
    publishedDateTime datetime(6)                       DEFAULT NULL comment '작성일자',
    createdBy         bigint(20)                        DEFAULT NULL,
    createdDateTime   datetime(6)                       DEFAULT NULL,
    modifiedBy        bigint(20)                        DEFAULT NULL,
    modifiedDateTime  datetime(6)                       DEFAULT NULL
) comment '테크 블로그 포스트';

CREATE TABLE SlackWebhooks
(
    slackWebhookSeq  bigint(20) NOT NULL primary key,
    slackWebhookEnum enum ('TECH_BLOG_SCRAP') DEFAULT NULL,
    url              varchar(255)             DEFAULT NULL,
    createdBy        bigint(20)               DEFAULT NULL,
    createdDateTime  datetime(6)              DEFAULT NULL,
    modifiedBy       bigint(20)               DEFAULT NULL,
    modifiedDateTime datetime(6)              DEFAULT NULL
) comment '슬랙 웹훅 설정';


CREATE TABLE Users
(
    userSeq                BIGINT              NOT NULL PRIMARY KEY,               -- 고유 사용자 순번
    id                     VARCHAR(50) UNIQUE  NOT NULL,                           -- 사용자 이름 (로그인 ID)
    # socialProviderEnum     ENUM ('LOCAL', 'GOOGLE', 'NAVER', 'KAKAO', 'FACEBOOK', 'APPLE') NOT NULL,
    password               VARCHAR(255)        NULL,                               -- 비밀번호 (암호화된 해시 값)
    name                   VARCHAR(100),                                           -- 사용자 실명
    email                  VARCHAR(100) UNIQUE NOT NULL,                           -- 이메일
    isEmailVerified        BOOLEAN                               DEFAULT FALSE,    -- 이메일 인증 여부
    mobileNumber           VARCHAR(20),                                            -- 전화번호
    isMobileNumberVerified BOOLEAN                               DEFAULT FALSE,    -- 전화번호 인증 여부
    birthDate              DATE,                                                   -- 생년월일
    genderEnum             ENUM ('MALE', 'FEMALE', 'OTHER'),                       -- 성별
    userStatusEnum         ENUM ('ACTIVE', 'INACTIVE', 'BANNED') DEFAULT 'ACTIVE', -- 계정 상태
    roleEnum               VARCHAR(255),                                           -- 사용자 권한 (관리자/사용자 등)
    lastLoginDateTime      datetime(6)         NULL,                               -- 마지막 로그인 일자
    createdBy              bigint(20)                            DEFAULT NULL,
    createdDateTime        datetime(6)                           DEFAULT NULL,
    modifiedBy             bigint(20)                            DEFAULT NULL,
    modifiedDateTime       datetime(6)                           DEFAULT NULL
) comment '사용자';
