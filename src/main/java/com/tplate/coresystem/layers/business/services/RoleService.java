package com.tplate.coresystem.layers.business.services;

import com.tplate.coresystem.layers.access.dtos.RoleDtoIn;
import com.tplate.coresystem.layers.business.validators.RoleValidator;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleValidator validator;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Goal: Read all roles (not deleted)
     *
     * @return roles (not deleted)
     */
    @Transactional(rollbackOn = Exception.class)
    public List<RoleModel> readNotDeleted() {
        return this.repository.findAllNotDeleted();
    }

    /**
     * Goal: Create role (runs validations over role dto).
     *
     * @param dto contains all fields to create a new role.
     * @return role created.
     */
    @Transactional(rollbackOn = Exception.class)
    public RoleModel createByDto(RoleDtoIn dto) {

        this.validator.validate(dto);

        RoleModel model = new RoleModel();

        return this.saveOrUpdate(dto, model);
    }

    /**
     * Goal: Update role by Dto and Id. (Apply validations over dto and id)
     *
     * @param dto contains all fields to update.
     * @param id  role.
     * @return role updated.
     */
    @Transactional(rollbackOn = Exception.class)
    public RoleModel updateByDto(RoleDtoIn dto, Long id) {

        this.validator.validate(dto, id);

        return this.saveOrUpdate(dto, this.repository.getById(id));

    }

    /**
     * Goal: Save or Update role.
     *
     * @param dto   contains all fields for update/create. All must be validated!
     * @param model to update/create.
     * @return model updated or created.
     */
    private RoleModel saveOrUpdate(RoleDtoIn dto, RoleModel model) {

        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());

        model.setPermissions(
                dto.getPermissionIds()
                        .stream()
                        .map(pId -> this.permissionRepository.getById(pId))
                        .collect(Collectors.toList())
        );

        return this.repository.save(model);

    }

}
