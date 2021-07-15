package com.tplate.coresystem.shared.persistence.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class ParametricModel extends BaseModel {

    @Column(name = "name")
    protected String name;

    @Column(name = "display_name")
    protected String displayName;

    @Column(name = "description")
    protected String description;

}
