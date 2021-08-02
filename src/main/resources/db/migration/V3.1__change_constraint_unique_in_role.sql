ALTER TABLE role DROP CONSTRAINT UQ_name_role;

ALTER TABLE permission
    ADD CONSTRAINT UQ_name_deletedAt_role UNIQUE (name, deleted_at);