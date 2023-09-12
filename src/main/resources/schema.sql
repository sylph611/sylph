DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT COMMENT 'PK'
        primary key,
    provider     VARCHAR(30)  NOT NULL COMMENT '공급자(구글,네이버,카카오)',
    user_id      VARCHAR(100) NOT NULL COMMENT '유저아이디',
    name         VARCHAR(50)  NULL COMMENT '성명',
    nickname     VARCHAR(50)  NULL COMMENT '닉네임',
    email        VARCHAR(100) NULL COMMENT '이메일',
    role         VARCHAR(50)  NOT NULL COMMENT '권한',
    created_date timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    created_by   bigint                              NOT NULL COMMENT '생성자',
    updated_date timestamp default CURRENT_TIMESTAMP NOT NULL COMMENT '수정일시',
    updated_by   bigint                              NOT NULL COMMENT '수정자'
);