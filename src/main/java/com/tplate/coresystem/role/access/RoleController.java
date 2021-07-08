package com.tplate.coresystem.role.access;

import com.tplate.coresystem.role.business.RoleService;
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
     * Goal: read not deleted roles.
     * Audit fields are hidden.
     * @return not deleted roles.
     */
    @GetMapping(Endpoints.ROLES)
    public ResponseDto readNotDeleted() {

        final Object model = this.service.readNotDeleted();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, RoleDtoOut[].class)
                .build();

    }

}
