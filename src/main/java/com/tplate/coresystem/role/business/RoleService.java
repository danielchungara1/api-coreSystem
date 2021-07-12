package com.tplate.coresystem.role.business;

import com.tplate.coresystem.permission.business.PermissionService;
import com.tplate.coresystem.role.access.RoleDtoIn;
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

    @Autowired
    private RoleValidator validator;

    @Autowired
    private PermissionService permissionService;

    /**
     * Goal: read all not deleted roles
     *
     * @return not deleted roles
     */
    @Transactional(rollbackOn = Exception.class)
    public List<RoleModel> readNotDeleted() {
        return this.repository.findAllNotDeleted();
    }

    /**
     * Goal: create new role
     *
     * @param dto contains all necessary fields to create a role
     * @return role created
     */
    @Transactional(rollbackOn = Exception.class)
    public RoleModel createByDto(RoleDtoIn dto) {

        // Build model
        RoleModel model = RoleModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .displayName(dto.getDisplayName())
                .permissions(this.permissionService.getModels(dto.getPermissionIds()))
                .build();

        // Validate model
        this.validator.validateModel(model);

        return this.repository.save(model);

    }
}
