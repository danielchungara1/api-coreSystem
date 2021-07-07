package com.tplate.coresystem.permission.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PermissionDtoIn {

    private String displayName;
    private String description;
    private Boolean enabled;

}
