package com.tplate.coresystem.permission.shared;

import com.tplate.coresystem.layers.access.dtos.PermissionOutDto;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.shared.Constants;

public class PermissionFactory {


    public static PermissionModel MODEL_OK =

            PermissionModel.builder()
                    .name(Constants.PERMISSION1)
                    .description(Constants.PERMISSION1)
                    .displayName(Constants.PERMISSION1)
                    .id(Constants.LONG_ONE)
                    .build();

    public static PermissionOutDto DTO_OK() {
        return PermissionOutDto.builder()
                .name(Constants.PERMISSION1)
                .description(Constants.PERMISSION1)
                .displayName(Constants.PERMISSION1)
                .build();
    }
}
