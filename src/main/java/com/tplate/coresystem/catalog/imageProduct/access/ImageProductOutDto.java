package com.tplate.coresystem.catalog.imageProduct.access;

import com.tplate.coresystem.shared.dtos.OutDto;
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
public class ImageProductOutDto extends OutDto {

    private String name;

    private String type;

    private String url;

    private Long size;

}
