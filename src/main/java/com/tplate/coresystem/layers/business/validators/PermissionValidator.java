package com.tplate.coresystem.layers.business.validators;

import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.access.ParametricDto;
import com.tplate.coresystem.shared.business.ParametricValidator;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator extends ParametricValidator<PermissionRepository>{

    /**
     * Check Validation Rules for Update:
     *  - Description is required
     *  - Display name is required
     *  - Id must exist
     *  If any validation fails is throws a business exception.
     */
    public void validate(ParametricDto dto, Long id) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.idMustExist(id);
    }

}
