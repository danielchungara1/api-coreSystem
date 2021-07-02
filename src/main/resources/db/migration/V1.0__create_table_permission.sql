CREATE TABLE permission
(
    -- Base
    id           BIGSERIAL    NOT NULL,

    -- Auditory
    created_by   VARCHAR(256) NOT NULL DEFAULT 'SYSTEM',
    created_at   TIMESTAMP    NOT NULL DEFAULT NOW(),

    updated_last_by   VARCHAR(256)          DEFAULT NULL,
    updated_last_at   TIMESTAMP             DEFAULT NULL,

    -- Parametric
    name         VARCHAR(256) NOT NULL,
    description  VARCHAR(512)          DEFAULT NULL,
    display_name VARCHAR(256)          DEFAULT NULL,

    -- Custom
    enabled      BOOLEAN               DEFAULT '1'

);
