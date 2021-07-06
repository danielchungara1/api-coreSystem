package com.tplate.coresystem.permission.access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDtoIn {
    private String displayName;
    private String description;
    private Boolean enabled;
}
