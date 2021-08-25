ALTER TABLE product
    ADD CONSTRAINT PK_product PRIMARY KEY (id),
    ADD CONSTRAINT UQ_product_code UNIQUE (code),
    ADD CONSTRAINT FK_product_to_brand FOREIGN KEY (brand_id) REFERENCES brand(id);
