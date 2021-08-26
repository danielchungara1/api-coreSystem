package com.tplate.coresystem.security.role;

import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.controllers.CreatableController;
import com.tplate.coresystem.core.controllers.DeletableController;
import com.tplate.coresystem.core.controllers.SearchableController;
import com.tplate.coresystem.core.controllers.UpdatableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UpdatableController<
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
