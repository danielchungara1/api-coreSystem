package com.tplate.coresystem.security.role.access;

import com.tplate.coresystem.security.role.persistence.RoleModel;
import com.tplate.coresystem.security.role.persistence.RoleRepository;
import com.tplate.coresystem.security.role.business.RoleService;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.controllers.CreatableController;
import com.tplate.coresystem.shared.controllers.DeletableController;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.controllers.UpdatableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
