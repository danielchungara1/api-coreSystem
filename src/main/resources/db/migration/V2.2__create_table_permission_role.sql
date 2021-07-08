CREATE TABLE permission_role
(
    -- Base
    id           BIGSERIAL    NOT NULL,

    -- Custom
    permission_id BIGINT DEFAULT NULL,
    role_id BIGINT DEFAULT NULL

);
