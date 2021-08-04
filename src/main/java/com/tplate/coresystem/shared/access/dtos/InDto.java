package com.tplate.coresystem.shared.access.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.tplate.coresystem.layers.access.dtos.RoleInDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public  abstract class InDto {
    // For grouping IN DTOs (declarative motivation)
}
