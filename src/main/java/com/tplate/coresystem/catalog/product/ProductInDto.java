package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.core.dtos.InDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductInDto extends InDto {

    @EqualsAndHashCode.Include
    private String code;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private Long brandId;

}
