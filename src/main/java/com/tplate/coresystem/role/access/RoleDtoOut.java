package com.tplate.coresystem.role.access;

import com.tplate.coresystem.permission.access.PermissionDtoOut;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDtoOut {

    private Long id;
    private String name;
    private String description;
    private String displayName;
    private List<PermissionDtoOut> permissions;

}
