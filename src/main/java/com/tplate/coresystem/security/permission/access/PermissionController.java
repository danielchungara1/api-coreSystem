package com.tplate.coresystem.security.permission.access;

import com.tplate.coresystem.security.permission.persistence.PermissionModel;
import com.tplate.coresystem.security.permission.persistence.PermissionRepository;
import com.tplate.coresystem.security.permission.business.PermissionService;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.PERMISSIONS)
@ConditionalOnExpression("${controller.permission.enabled:false}")
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
