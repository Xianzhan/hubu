DROP TABLE IF EXISTS hb_corp;
CREATE TABLE hb_corp
(
    id        BIGINT(11) UNSIGNED NOT NULL COMMENT 'ID',
    corp_id   VARCHAR(10)         NOT NULL COMMENT '企业 ID',
    corp_name VARCHAR(10)         NOT NULL COMMENT '企业名称',
    is_del    TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '逻辑删除，0-未删除，1-已删除',
    CONSTRAINT hb_corp_pk
        PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT '企业表';