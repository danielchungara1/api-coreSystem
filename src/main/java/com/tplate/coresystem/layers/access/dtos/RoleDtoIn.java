package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.ParametricDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDtoIn extends ParametricDto {

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return Objects.requireNonNullElseGet(this.permissionIds, ArrayList::new);
    }

}
