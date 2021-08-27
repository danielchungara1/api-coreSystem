package com.tplate.coresystem.security.role;

import com.tplate.coresystem.security.permission.PermissionSMOutDto;
import com.tplate.coresystem.core.dtos.OutDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleOutDto extends OutDto {

    private String name;

    private String description;

    private String displayName;

    private List<PermissionSMOutDto> permissions;

}
