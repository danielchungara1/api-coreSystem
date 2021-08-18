ALTER TABLE product_image
    ADD CONSTRAINT PK_image_product PRIMARY KEY (id),
    ADD CONSTRAINT FK_image_product_to_product FOREIGN KEY (product_id) REFERENCES product(id);
