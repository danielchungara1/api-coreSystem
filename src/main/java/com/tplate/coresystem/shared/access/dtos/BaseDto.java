package com.tplate.coresystem.shared.access.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
public abstract class BaseDto {

    @EqualsAndHashCode.Include
    protected Long id;

}