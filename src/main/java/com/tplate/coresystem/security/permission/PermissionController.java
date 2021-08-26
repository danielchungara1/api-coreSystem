package com.tplate.coresystem.security.permission;

import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.controllers.SearchableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.PERMISSIONS)
public class PermissionController implements
        SearchableController<
                PermissionService,
                PermissionRepository,
                PermissionModel,
                PermissionOutDto
                > {

    @Autowired
    private PermissionService service;

    private final Class CLAZZ_DTO = PermissionOutDto.class;


    @Override
    public PermissionService getService() {
        return this.service;
    }

    @Override
    public Class<PermissionOutDto> getClassOutDTO() {
        return CLAZZ_DTO;
    }
}
