CREATE TABLE permission
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
    name            VARCHAR(256) NOT NULL,
    description     VARCHAR(512) NOT NULL,
    display_name    VARCHAR(256) NOT NULL

);
