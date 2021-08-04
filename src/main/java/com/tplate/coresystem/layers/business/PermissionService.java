package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.business.services.SearchableService;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
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
