package com.tplate.coresystem.security.permission.access;

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
public class PermissionOutDto extends OutDto {

    private String name;

    private String description;

    private String displayName;
}
