package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.RoleDto;
import com.tplate.coresystem.layers.business.RoleService;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.access.BaseController;
import com.tplate.coresystem.shared.access.Endpoints;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.ROLES)
public class RoleController extends BaseController<
        RoleService,
        RoleRepository,
        RoleModel,
        RoleDto
        > {

}
