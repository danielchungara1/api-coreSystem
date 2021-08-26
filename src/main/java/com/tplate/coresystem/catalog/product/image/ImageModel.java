package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.core.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Where(clause = "deleted_at IS NULL")
public class ImageModel extends BaseModel {

    @Column(name = "data")
    @Lob
    private byte[] data;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "main")
    private Boolean main;

    @Transient
    private String url;

}
