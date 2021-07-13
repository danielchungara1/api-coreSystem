package com.tplate.coresystem.shared.access;

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
