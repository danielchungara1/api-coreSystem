package com.tplate.coresystem.security.permission.business;

import com.tplate.coresystem.security.permission.persistence.PermissionModel;
import com.tplate.coresystem.security.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.services.SearchableService;
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
