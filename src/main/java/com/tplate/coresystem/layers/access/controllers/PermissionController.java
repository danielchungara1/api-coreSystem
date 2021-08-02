package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.access.BaseController;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.PERMISSIONS)
public class PermissionController extends BaseController<
        PermissionService,
        PermissionRepository,
        PermissionModel,
        PermissionDto
        > {

    @Override
    public ResponseDto deleteById(Long id) {
        throw new BusinessException("Delete permission not permitted.");
    }

    @Override
    public ResponseDto create(PermissionDto dto) {
        throw new BusinessException("Create permission not permitted.");
    }

    @Override
    public ResponseDto update(PermissionDto dto, Long id) {
        throw new BusinessException("Update permission not permitted.");
    }

}
