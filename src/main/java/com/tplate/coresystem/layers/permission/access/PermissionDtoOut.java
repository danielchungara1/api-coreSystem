package com.tplate.coresystem.layers.permission.access;

import com.tplate.coresystem.layers.shared.persistence.IParametric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDtoOut implements IParametric {

    private Long id;
    private String name;
    private String description;
    private String displayName;

}
