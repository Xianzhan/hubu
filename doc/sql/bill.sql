DROP TABLE IF EXISTS hb_car_bill;
CREATE TABLE hb_car_bill
(
    id            BIGINT(11) UNSIGNED NOT NULL COMMENT 'ID',
    corp_id       VARCHAR(10)         NOT NULL COMMENT '企业 ID',
    supplier_code VARCHAR(10)         NOT NULL COMMENT '供应商 ID',
    order_id      VARCHAR(50)         NOT NULL COMMENT '订单 ID',
    is_del        TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '逻辑删除，0-未删除，1-已删除',
    CONSTRAINT hb_car_bill_pk
        PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT '用车账单';

# 分片
DROP TABLE IF EXISTS hb_car_bill_0;
CREATE TABLE hb_car_bill_0
(
    id            BIGINT(11) UNSIGNED NOT NULL COMMENT 'ID',
    corp_id       VARCHAR(10)         NOT NULL COMMENT '企业 ID',
    supplier_code VARCHAR(10)         NOT NULL COMMENT '供应商 ID',
    order_id      VARCHAR(50)         NOT NULL COMMENT '订单 ID',
    is_del        TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '逻辑删除，0-未删除，1-已删除',
    CONSTRAINT hb_car_bill_pk
        PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT '用车账单';

DROP TABLE IF EXISTS hb_car_bill_1;
CREATE TABLE hb_car_bill_1
(
    id            BIGINT(11) UNSIGNED NOT NULL COMMENT 'ID',
    corp_id       VARCHAR(10)         NOT NULL COMMENT '企业 ID',
    supplier_code VARCHAR(10)         NOT NULL COMMENT '供应商 ID',
    order_id      VARCHAR(50)         NOT NULL COMMENT '订单 ID',
    is_del        TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '逻辑删除，0-未删除，1-已删除',
    CONSTRAINT hb_car_bill_pk
        PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT '用车账单';