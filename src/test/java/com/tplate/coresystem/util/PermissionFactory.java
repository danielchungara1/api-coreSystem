package com.tplate.coresystem.util;

import com.tplate.coresystem.permission.persistence.PermissionModel;

public class PermissionFactory  {

    public static final String PERMISSION1 = "PERMISSION1";

    public static PermissionModel getModelOK() {
        return PermissionModel.builder()
                .name(PERMISSION1)
                .description(PERMISSION1)
                .displayName(PERMISSION1)
                .id(PrimitiveFactory.LONG_ONE)
                .build();
    }

}
