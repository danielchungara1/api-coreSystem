package com.tplate.coresystem.util;

import com.tplate.coresystem.permission.access.PermissionOutDto;
import com.tplate.coresystem.permission.persistence.PermissionModel;

public class PermissionFactory {

    public static final String PERMISSION1 = "PERMISSION1";

    public static final Long LONG_ONE = 1L;

    public static final Long LONG_ZERO = 0L;


    public static PermissionModel MODEL_OK =

            PermissionModel.builder()
                    .name(PERMISSION1)
                    .description(PERMISSION1)
                    .displayName(PERMISSION1)
                    .id(LONG_ONE)
                    .build();

    public static PermissionOutDto DTO_OK() {
        return PermissionOutDto.builder()
                .name(PERMISSION1)
                .description(PERMISSION1)
                .displayName(PERMISSION1)
                .build();
    }
}
