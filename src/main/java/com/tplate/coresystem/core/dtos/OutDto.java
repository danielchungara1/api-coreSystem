package com.tplate.coresystem.core.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class OutDto {

    @EqualsAndHashCode.Include
    protected Long id;

}
