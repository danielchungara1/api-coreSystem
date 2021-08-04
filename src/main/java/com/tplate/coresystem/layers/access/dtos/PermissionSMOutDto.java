package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.OutDto;
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
public class PermissionSMOutDto extends OutDto {

    private String name;

    private String displayName;
}
