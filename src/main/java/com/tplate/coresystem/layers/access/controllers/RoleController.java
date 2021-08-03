package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.access.dtos.RoleDto;
import com.tplate.coresystem.layers.business.RoleService;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.access.BaseController;
import com.tplate.coresystem.shared.access.DeletableController;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.SearchableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.ROLES)
public class RoleController implements SearchableController<
        RoleService,
        RoleRepository,
        RoleModel,
        RoleDto
        >,
        DeletableController <
                RoleService,
                RoleRepository,
                RoleModel
                >
{

    @Autowired
    private RoleService service;

    private final Class CLAZZ_DTO = RoleDto.class;

    @Override
    public RoleService getService() {
        return service;
    }

    @Override
    public Class<RoleDto> getClassDTO() {
        return this.CLAZZ_DTO;
    }
}
