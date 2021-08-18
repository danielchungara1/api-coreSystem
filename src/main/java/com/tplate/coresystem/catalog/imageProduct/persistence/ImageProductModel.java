package com.tplate.coresystem.catalog.imageProduct.persistence;

import com.tplate.coresystem.catalog.product.persistence.ProductModel;
import com.tplate.coresystem.shared.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "image_product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class ImageProductModel extends BaseModel {

    @Column(name = "data")
    @Lob
    protected byte[] data;

    @Column(name = "name")
    protected String name;

    @Column(name = "type")
    protected String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private ProductModel product;

}
