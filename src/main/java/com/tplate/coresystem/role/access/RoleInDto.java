package com.tplate.coresystem.role.access;

import com.tplate.coresystem.shared.dtos.InDto;
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
