package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto {

    private String name;

    private String description;

    private String displayName;

    private List<PermissionSmDto> permissions;

}
