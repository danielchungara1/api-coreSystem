package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * Goal: Find all permissions.
     *
     * @return all permissions.
     */
    @GetMapping(Endpoints.PERMISSIONS)
    public ResponseDto findAll() {

        final Object model = this.permissionService.findAll();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, PermissionDto[].class)
                .build();

    }

    /**
     * Goal: Update permission.
     *
     * @param dto contains updatable fields.
     * @param id  permission.
     * @return permission updated.
     */
    @PutMapping(Endpoints.PERMISSIONS + "/{id}")
    public ResponseDto updateByDto(@PathVariable Long id, @RequestBody PermissionDto dto) {

        final Object model = this.permissionService.updateByDto(id, dto);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, PermissionDto.class)
                .build();

    }
}
