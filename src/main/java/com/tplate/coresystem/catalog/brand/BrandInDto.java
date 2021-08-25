package com.tplate.coresystem.catalog.brand;

import com.tplate.coresystem.shared.dtos.InDto;
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
public class BrandInDto extends InDto {

    private String name;

}
