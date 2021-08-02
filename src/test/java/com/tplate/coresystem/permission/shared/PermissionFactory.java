package com.tplate.coresystem.permission.shared;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.shared.Constants;

import java.util.List;

public class PermissionFactory {


    public static PermissionModel MODEL_OK =

            PermissionModel.builder()
                    .name(Constants.PERMISSION1)
                    .description(Constants.PERMISSION1)
                    .displayName(Constants.PERMISSION1)
                    .id(Constants.LONG_ONE)
                    .build();

    public static PermissionDto DTO_OK() {
        return PermissionDto.builder()
                .name(Constants.PERMISSION1)
                .description(Constants.PERMISSION1)
                .displayName(Constants.PERMISSION1)
                .build();
    }
}
