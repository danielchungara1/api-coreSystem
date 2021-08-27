package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.core.dtos.OutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ImageOutDto extends OutDto {

    private String name;

    private String type;

    private Boolean main;

    private String url;

}
