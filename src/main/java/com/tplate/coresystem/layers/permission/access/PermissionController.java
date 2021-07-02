package com.tplate.coresystem.layers.permission.access;

import com.tplate.coresystem.layers.shared.access.Endpoints;
import com.tplate.coresystem.layers.permission.business.PermissionService;
import com.tplate.coresystem.layers.shared.access.Messages;
import com.tplate.coresystem.layers.shared.access.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return permission updated
     */

    /**
     * Goal: disable/enable a permission
     * @return permission updated
     */

}
