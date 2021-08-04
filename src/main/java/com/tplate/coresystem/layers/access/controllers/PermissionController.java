package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.shared.access.SearchableController;
import com.tplate.coresystem.layers.access.dtos.PermissionOutDto;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.shared.access.Endpoints;
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
                >
{

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
