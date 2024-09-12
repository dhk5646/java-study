CREATE TABLE TechBlogPosts
(
    techBlogPostSeq bigint(20) NOT NULL primary key,
    techBlogEnum    enum ('KAKAO','NAVER','WOOWAHAN') DEFAULT NULL,
    title           varchar(255)                      DEFAULT NULL,
    url             varchar(255)                      DEFAULT NULL,
    createdBy       bigint(20)                        DEFAULT NULL,
    createdDate     datetime(6)                       DEFAULT NULL,
    modifiedBy      bigint(20)                        DEFAULT NULL,
    modifiedDate    datetime(6)                       DEFAULT NULL
) comment '테크 블로그 포스트';

CREATE TABLE SlackWebhooks
(
    slackWebhookSeq  bigint(20) NOT NULL primary key,
    slackWebhookEnum enum ('TECH_BLOG_SCRAP') DEFAULT NULL,
    url              varchar(255)             DEFAULT NULL,
    createdBy        bigint(20)               DEFAULT NULL,
    createdDate      datetime(6)              DEFAULT NULL,
    modifiedBy       bigint(20)               DEFAULT NULL,
    modifiedDate     datetime(6)              DEFAULT NULL
) comment '슬랙 웹훅 설정';