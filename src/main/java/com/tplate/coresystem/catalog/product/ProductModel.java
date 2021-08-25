package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.catalog.brand.BrandModel;
import com.tplate.coresystem.catalog.product.image.ImageModel;
import com.tplate.coresystem.shared.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Where(clause = "deleted_at IS NULL")
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
    @JoinColumn(name = "brand_id")
    private BrandModel brand;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<ImageModel> images;

    public List<ImageModel> getImages() {
        return this.images == null ? new ArrayList<>() : this.images;
    }

    public void addImage(ImageModel imageModel) {
        this.getImages().add(imageModel);
    }

}
