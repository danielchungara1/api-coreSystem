package com.tplate.coresystem.catalog.product.persistence;

import com.tplate.coresystem.catalog.brand.persistence.BrandModel;
import com.tplate.coresystem.shared.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class ProductModel extends BaseModel {

    @Column(name = "code")
    protected String code;

    @Column(name = "description")
    protected String description;

    @Column(name = "price")
    protected BigDecimal price;

    @Column(name = "stock")
    protected Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private BrandModel brand;

}
