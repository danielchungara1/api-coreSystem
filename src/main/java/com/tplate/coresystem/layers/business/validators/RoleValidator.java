package com.tplate.coresystem.layers.business.validators;

import com.tplate.coresystem.layers.access.dtos.RoleDtoIn;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.business.ParametricValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator extends ParametricValidator<RoleRepository>{

    @Autowired
    private PermissionValidator permissionValidator;

    /**
     * Goal: apply business rules (for create role)
     * - description is required
     * - displayName is required
     * - name is required and unique
     * - permissions are optional, if there are permissions check all ids.
     * @param dto contains fields to validate.
     */
    public void validate(RoleDtoIn dto) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.nameIsRequiredAndUnique(dto.getName());

        this.permissionValidator.idListMustExist(dto.getPermissionIds());
    }

    /**
     * Goal: apply business rules (for update role)
     * - description is required
     * - displayName is required
     * - name is required and unique
     * - permissions are optional, if there are permissions check all ids.
     * @param dto contains fields to validate.
     * @param id role
     */
    public void validate(RoleDtoIn dto, Long id) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.nameIsRequiredAndUnique(dto.getName(), id);

        this.permissionValidator.idListMustExist(dto.getPermissionIds());
    }
}
