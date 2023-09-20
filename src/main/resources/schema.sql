DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS place;
DROP TABLE IF EXISTS review;

CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT COMMENT 'PK'
        primary key,
    provider     VARCHAR(30)                         NOT NULL COMMENT '공급자(구글,네이버,카카오)',
    user_id      VARCHAR(100)                        NOT NULL COMMENT '유저 아이디',
    name         VARCHAR(50)                         NULL COMMENT '성명',
    nickname     VARCHAR(50)                         NULL COMMENT '닉네임',
    email        VARCHAR(100)                        NULL COMMENT '이메일',
    role         VARCHAR(50)                         NOT NULL COMMENT '권한',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 일시',
    created_by   BIGINT                              NOT NULL COMMENT '생성자',
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 일시',
    updated_by   BIGINT                              NOT NULL COMMENT '수정자'
) COMMENT = '사용자';

CREATE TABLE place
(
    id           BIGINT AUTO_INCREMENT COMMENT 'PK'
        primary  key,
    name                VARCHAR(100) NOT NULL COMMENT '식당명',
    address             VARCHAR(200) NULL COMMENT '주소',
    place_category      VARCHAR(100) NULL COMMENT '장소 대분류(음식점, 바, 카페 등)' ,
    place_sub_category  VARCHAR(100) NULL COMMENT '장소 소분류(중국집, 일식집, 디저트 카페, 다이닝펍 등)',
    region              VARCHAR(100) NULL COMMENT '지역 대분류(서울,경기,부산)',
    sub_region          VARCHAR(100) NULL COMMENT '지역 소분류(홍대, 강남 등)',
    business_hours      VARCHAR(100) NULL COMMENT '영업시간 (11:00 ~ 12:00)',
    phone_number        VARCHAR(100) NULL COMMENT '전화번호',
    latitude            DECIMAL(11,8) NULL COMMENT '위도',
    longitude           DECIMAL(11,8) NULL COMMENT '경도',
    deleted             BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제여부',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 일시',
    created_by   BIGINT                              NOT NULL COMMENT '생성자',
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 일시',
    updated_by   BIGINT                              NOT NULL COMMENT '수정자'
) COMMENT = '플레이스';

CREATE TABLE review
(
    id           BIGINT AUTO_INCREMENT COMMENT 'PK'
        primary  key,
    place_id     BIGINT NOT NULL COMMENT '장소',
    score        TINYINT NOT NULL COMMENT '점수',
    visit_date   DATE NULL COMMENT '방문 일자',
    title        VARCHAR(300)  NOT NULL COMMENT '리뷰 제목',
    content      VARCHAR(2000) NOT NULL COMMENT '리뷰 내용',
    deleted      BOOLEAN NOT NULL DEFAULT FALSE COMMENT '삭제여부',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성 일시',
    created_by   BIGINT                              NOT NULL COMMENT '생성자',
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '수정 일시',
    updated_by   BIGINT                              NOT NULL COMMENT '수정자'
) COMMENT = '리뷰';