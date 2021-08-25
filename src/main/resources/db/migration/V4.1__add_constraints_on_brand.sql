ALTER TABLE brand
    ADD CONSTRAINT PK_brand PRIMARY KEY (id),
    ADD CONSTRAINT UQ_name_brand UNIQUE (name);
