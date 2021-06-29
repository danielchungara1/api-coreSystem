package com.tplate.coresystem.layers.permission.business;

import com.tplate.coresystem.layers.permission.persistence.PermissionModel;
import com.tplate.coresystem.layers.permission.persistence.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Transactional(rollbackOn = Exception.class)
    public List<PermissionModel> readAll() {
        return this.repository.findAll();
    }

}
