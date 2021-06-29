package com.tplate.coresystem.layers.permission.access;

import com.tplate.coresystem.layers.shared.access.Endpoints;
import com.tplate.coresystem.layers.permission.business.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping(Endpoints.PERMISSIONS)
    public List<?> readAll() {
        return this.permissionService.readAll();
    }

}
