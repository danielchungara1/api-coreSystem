package com.tplate.coresystem.security.role.access;

import com.tplate.coresystem.security.permission.access.PermissionSMOutDto;
import com.tplate.coresystem.shared.dtos.OutDto;
import lombok.*;
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
