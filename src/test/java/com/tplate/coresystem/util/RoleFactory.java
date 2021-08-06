package com.tplate.coresystem.util;

import com.tplate.coresystem.role.access.RoleInDto;
import com.tplate.coresystem.role.persistence.RoleModel;

import java.util.List;

public class RoleFactory {

    public static final String ROLE1 = "ROLE1";

    public static RoleModel getModelOK() {
        return RoleModel.builder()
                .name(ROLE1)
                .description(ROLE1)
                .displayName(ROLE1)
                .id(PrimitiveFactory.LONG_ONE)
                .permissions(List.of(PermissionFactory.getModelOK()))
                .build();
    }

    public static RoleInDto getDtoInOk() {
        return RoleInDto.builder()
                .name(ROLE1)
                .description(ROLE1)
                .displayName(ROLE1)
                .permissionsId(List.of(PrimitiveFactory.LONG_ONE))
                .build();
    }

}
