package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.shared.dtos.InDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageInDto extends InDto {

    private Boolean main;

}
