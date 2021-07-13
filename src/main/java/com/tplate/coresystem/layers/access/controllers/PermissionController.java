package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.business.services.PermissionService;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * Goal: Read all permissions.
     *
     * @return all permissions
     */
    @GetMapping(Endpoints.PERMISSIONS)
    public ResponseDto readAll() {

        final Object model = this.permissionService.readAll();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, PermissionDto[].class)
                .build();

    }

    /**
     * Goal: update permission
     *
     * @param dto contains updatable fields
     * @param id  of the permission
     * @return permission updated
     */
    @PutMapping(Endpoints.PERMISSIONS + "/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody PermissionDto dto) {

        final Object model = this.permissionService.updateByDto(id, dto);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, PermissionDto.class)
                .build();

    }
}
