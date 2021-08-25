package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.catalog.brand.BrandOutDto;
import com.tplate.coresystem.catalog.product.image.ImageOutDto;
import com.tplate.coresystem.shared.dtos.OutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutDto extends OutDto {

    private String code;

    private String description;

    private BigDecimal price;

    private Long stock;

    private BrandOutDto brand;

    private List<ImageOutDto> images;

}
