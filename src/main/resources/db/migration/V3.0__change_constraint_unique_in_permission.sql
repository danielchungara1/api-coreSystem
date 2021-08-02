ALTER TABLE permission DROP CONSTRAINT UQ_name_permission;

ALTER TABLE permission
    ADD CONSTRAINT UQ_name_deletedAt_permission UNIQUE (name, deleted_at);