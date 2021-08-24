ALTER TABLE product_image
    ADD CONSTRAINT PK_product_image PRIMARY KEY (id),
    ADD CONSTRAINT FK_product_image_to_product FOREIGN KEY (product_id) REFERENCES product(id);
