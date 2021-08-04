package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.OutDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionOutDto extends OutDto {

    private String name;

    private String description;

    private String displayName;
}
