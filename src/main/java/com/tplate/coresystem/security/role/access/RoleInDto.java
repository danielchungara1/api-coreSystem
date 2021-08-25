package com.tplate.coresystem.security.role.access;

import com.tplate.coresystem.shared.dtos.InDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleInDto extends InDto {

    @EqualsAndHashCode.Include
    private String name;

    private String description;

    private String displayName;

    private List<Long> permissionsId;


}
