ALTER TABLE permission_role
    ADD CONSTRAINT PK_permission_role PRIMARY KEY (id),
    ADD CONSTRAINT UQ_permission_role UNIQUE (permission_id, role_id),
    ADD CONSTRAINT FK_permission_role_to_role FOREIGN KEY (role_id) REFERENCES role(id),
    ADD CONSTRAINT FK_permission_role_to_permission FOREIGN KEY (permission_id) REFERENCES permission(id);
