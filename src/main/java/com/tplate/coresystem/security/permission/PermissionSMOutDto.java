package com.tplate.coresystem.security.permission;

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
public class PermissionSMOutDto extends OutDto {

    private String name;

    private String displayName;
}
