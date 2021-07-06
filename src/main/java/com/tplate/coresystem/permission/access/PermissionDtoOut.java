package com.tplate.coresystem.permission.access;

import com.tplate.coresystem.shared.persistence.IParametric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDtoOut implements IParametric {

    private Long id;
    private String name;
    private String description;
    private String displayName;
    private Boolean enabled;

}
