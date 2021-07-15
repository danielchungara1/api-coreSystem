package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDtoReduced extends BaseDto {

    private String name;

}
