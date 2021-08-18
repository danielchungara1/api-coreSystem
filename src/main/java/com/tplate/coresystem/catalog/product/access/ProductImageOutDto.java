package com.tplate.coresystem.catalog.product.access;

import com.tplate.coresystem.catalog.brand.access.BrandOutDto;
import com.tplate.coresystem.shared.dtos.OutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageOutDto extends OutDto {

    private String name;

    private String type;

}