package com.tplate.coresystem.layers.access.controllers;

import com.tplate.coresystem.layers.access.dtos.RoleDtoIn;
import com.tplate.coresystem.layers.access.dtos.RoleDtoOut;
import com.tplate.coresystem.layers.business.RoleService;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleService service;

    /**
     * Goal: Find all roles (not deleted).
     *
     * @return roles (not deleted).
     */
    @GetMapping(Endpoints.ROLES)
    public ResponseDto findAll() {

        final Object model = this.service.findAll();

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
    public ResponseDto createByDto(@RequestBody RoleDtoIn dto) {

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
    public ResponseDto updateByDto(@RequestBody RoleDtoIn dto, @PathVariable Long id) {

        Object model = this.service.updateByDto(dto, id);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, RoleDtoOut.class)
                .build();

    }

    /**
     * Goal: Delete role by Id.
     *
     * @param id role
     * @return deleted message
     */
    @DeleteMapping(Endpoints.ROLES + "/{id}")
    public ResponseDto deleteById(@PathVariable Long id) {

        this.service.deleteById(id);

        return ResponseDto.builder()
                .message(Messages.DELETED)
                .build();

    }
}
