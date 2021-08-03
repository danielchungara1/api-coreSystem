package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto extends BaseDto {

    private String name;

    private String description;

    private String displayName;
}
