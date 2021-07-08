package com.tplate.coresystem.role.business;

import com.tplate.coresystem.permission.business.PermissionValidator;
import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.role.persistence.RoleModel;
import com.tplate.coresystem.role.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    /**
     * Goal: read all not deleted roles
     * @return not deleted roles
     */
    @Transactional(rollbackOn = Exception.class)
    public List<RoleModel> readNotDeleted() {
        return this.repository.findAllNotDeleted();
    }

}
