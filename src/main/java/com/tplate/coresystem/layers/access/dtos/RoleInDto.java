package com.tplate.coresystem.layers.access.dtos;

import com.tplate.coresystem.shared.access.dtos.InDto;
import com.tplate.coresystem.shared.access.dtos.OutDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleInDto extends InDto {

    private String name;

    private String description;

    private String displayName;

    private List<Long> permissionsId;


}
