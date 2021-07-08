package com.tplate.coresystem.role.access;

import com.tplate.coresystem.permission.access.PermissionDtoOut;
import com.tplate.coresystem.shared.persistence.IParametric;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDtoOut implements IParametric {

    private Long id;
    private String name;
    private String description;
    private String displayName;
    private List<PermissionDtoOut> permissions;

}
