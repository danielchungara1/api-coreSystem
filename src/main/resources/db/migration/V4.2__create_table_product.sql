CREATE TABLE product
(
    -- Base
    id              BIGSERIAL    NOT NULL,

    -- Auditory
    created_by      VARCHAR(256) NOT NULL DEFAULT 'SYSTEM',
    created_at      TIMESTAMP    NOT NULL DEFAULT NOW(),

    updated_last_by VARCHAR(256)          DEFAULT NULL,
    updated_last_at TIMESTAMP             DEFAULT NULL,

    deleted_by      VARCHAR(256)          DEFAULT NULL,
    deleted_at      TIMESTAMP             DEFAULT NULL,

    -- Parametric
    code            VARCHAR(256)          DEFAULT NULL,
    description     VARCHAR(256)          DEFAULT NULL,
    price           NUMERIC(11, 3)        DEFAULT NULL,
    stock           INTEGER               DEFAULT NULL,
    brand_id        BIGINT                DEFAULT NULL

);
