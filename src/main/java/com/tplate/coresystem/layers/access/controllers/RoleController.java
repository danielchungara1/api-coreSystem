package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.RoleInDto;
import com.tplate.coresystem.layers.access.dtos.RoleOutDto;
import com.tplate.coresystem.layers.business.RoleService;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.access.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.ROLES)
public class RoleController implements
        CreatableController<
                RoleService,
                RoleRepository,
                RoleModel,
                RoleOutDto,
                RoleInDto
                >,
        UpdatableController <
                RoleService,
                RoleRepository,
                RoleModel,
                RoleOutDto,
                RoleInDto
                >,
        SearchableController<
                RoleService,
                RoleRepository,
                RoleModel,
                RoleOutDto
                >,
        DeletableController<
                RoleService,
                RoleRepository,
                RoleModel
                > {

    @Autowired
    private RoleService service;

    private final Class CLAZZ_OUT_DTO = RoleOutDto.class;

    @Override
    public RoleService getService() {
        return service;
    }

    @Override
    public Class<RoleOutDto> getClassOutDTO() {
        return this.CLAZZ_OUT_DTO;
    }

}
