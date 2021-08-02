package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends BaseDto {

    private String name;

    private String description;

    private String displayName;

    private List<Long> permissionIds;

    private List<PermissionDto> permissions;

    public List<Long> getPermissionIds() {
        return Objects.requireNonNullElseGet(this.permissionIds, ArrayList::new);
    }

}
