package com.tplate.coresystem.shared.access.dtos;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class ParametricDto extends BaseDto {

    protected String name;

    protected String displayName;

    protected String description;

}
