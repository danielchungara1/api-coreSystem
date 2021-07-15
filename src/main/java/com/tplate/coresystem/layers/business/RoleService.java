package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.access.dtos.RoleDtoIn;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.business.services.ParametricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService extends ParametricService<RoleRepository> {

    @Autowired
    private PermissionService permissionService;

    /**
     * Goal: Find all roles (not deleted)
     *
     * @return roles (not deleted)
     */
    @Transactional(rollbackOn = Exception.class)
    public List<RoleModel> findAll() {
        return this.repository.findAll();
    }

    /**
     * Goal: Create role (runs validations over dto).
     *
     * @param dto contains all fields to create a new role.
     * @return role created.
     */
    @Transactional(rollbackOn = Exception.class)
    public RoleModel createByDto(RoleDtoIn dto) {

        this.validate(dto);

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

        this.validate(dto, id);

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
                        .map(pId -> (PermissionModel)this.permissionService.getById(pId))
                        .collect(Collectors.toList())
        );

        return this.repository.save(model);

    }

    /**
     * Goal: apply business rules (for create role)
     * - description is required
     * - displayName is required
     * - name is required and unique
     * - permissions are optional, if there are permissions check all ids.
     *
     * @param dto contains fields to validate.
     */
    public void validate(RoleDtoIn dto) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.nameIsRequiredAndUnique(dto.getName());

        this.permissionService.idListMustExist(dto.getPermissionIds());
    }

    /**
     * Goal: apply business rules (for update role)
     * - description is required
     * - displayName is required
     * - name is required and unique
     * - permissions are optional, if there are permissions check all ids.
     * - Obs: When updating is possible send the same name.
     *
     * @param dto contains fields to validate.
     * @param id  role
     */
    public void validate(RoleDtoIn dto, Long id) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.nameIsRequiredAndUnique(dto.getName(), id);
        super.idMustExist(id);

        this.permissionService.idListMustExist(dto.getPermissionIds());
    }

}
