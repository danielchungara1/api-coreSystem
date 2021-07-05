package com.tplate.coresystem.permission.access;

import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.permission.business.PermissionService;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * Goal: read enabled permissions.
     * Audit fields are hidden.
     *
     * @return permissions
     */
    @GetMapping(Endpoints.PERMISSIONS)
    public ResponseDto readEnabled() {

        final Object model = this.permissionService.readEnabled();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, PermissionDtoOut[].class)
                .build();

    }

    /**
     * Goal: update a permission
     * @param dto contains updatable fields
     * @param id of the permission to update
     * @return permission updated
     */
    @PutMapping(Endpoints.PERMISSIONS + "/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody PermissionDtoIn dto) {

        final Object model = this.permissionService.updateByDto(id, dto);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, PermissionDtoOut.class)
                .build();

    }

    /**
     * Goal: disable/enable a permission
     * @return permission updated
     */

}
