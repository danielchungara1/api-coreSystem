package com.tplate.coresystem.security.permission;

import com.tplate.coresystem.core.services.SearchableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermissionService implements
        SearchableService<
                PermissionRepository,
                PermissionModel
                > {

    @Autowired
    private PermissionRepository repository;

    @Override
    public PermissionRepository getRepository() {
        return repository;
    }
}
