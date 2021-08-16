package com.tplate.coresystem.catalog.brand.persistence;

import com.tplate.coresystem.shared.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class BrandModel extends BaseModel {

    @Column(name = "name")
    protected String name;

    public BrandModel(String name) {
        this.name = name;
    }
}
