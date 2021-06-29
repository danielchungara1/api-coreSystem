ALTER TABLE permission
    ADD CONSTRAINT PK_permission PRIMARY KEY (id),
    ADD CONSTRAINT UQ_name_permission UNIQUE (name);
