package com.tplate.coresystem.role.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
public class RoleDtoIn {

    private String displayName;
    private String description;
    private String name;
    private Optional<List<Long>> permissionIds;

}
