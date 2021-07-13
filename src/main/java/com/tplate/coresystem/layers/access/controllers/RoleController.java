package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.RoleDtoIn;
import com.tplate.coresystem.layers.access.dtos.RoleDtoOut;
import com.tplate.coresystem.layers.business.services.RoleService;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleService service;

    /**
     * Goal: Read all roles (not deleted).
     *
     * @return all roles (not deleted).
     */
    @GetMapping(Endpoints.ROLES)
    public ResponseDto readNotDeleted() {

        final Object model = this.service.readNotDeleted();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, RoleDtoOut[].class)
                .build();

    }

    /**
     * Goal: Create role.
     *
     * @param dto contains fields to create a new role.
     * @return role created.
     */
    @PostMapping(Endpoints.ROLES)
    public ResponseDto create(@RequestBody RoleDtoIn dto) {

        Object model = this.service.createByDto(dto);

        return ResponseDto.builder()
                .message(Messages.CREATED)
                .data(model, RoleDtoOut.class)
                .build();

    }

    /**
     * Goal: Update role by Dto and Id.
     *
     * @param dto contains all fields to update.
     * @param id role
     * @return role updated
     */
    @PutMapping(Endpoints.ROLES + "/{id}")
    public ResponseDto update(@RequestBody RoleDtoIn dto, @PathVariable Long id) {

        Object model = this.service.updateByDto(dto, id);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, RoleDtoOut.class)
                .build();

    }
}
