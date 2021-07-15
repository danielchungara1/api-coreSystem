package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.ParametricDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDtoOut extends ParametricDto {

    private List<PermissionDtoReduced> permissions;
    
}
