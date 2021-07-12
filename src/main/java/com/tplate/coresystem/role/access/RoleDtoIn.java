package com.tplate.coresystem.role.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDtoIn {

    private String displayName;
    private String description;
    private String name;

}
