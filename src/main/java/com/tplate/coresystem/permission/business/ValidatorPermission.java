package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import com.tplate.coresystem.shared.business.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/**
 * Validates business rules
 */
public class ValidatorPermission {

    @Autowired
    PermissionRepository repository;

    /**
     * Rules:
     *  - Name is not updatable
     *  - Description is required and not blank
     *  - Display name is required and not blank
     *  - Enabled is optional, is true by default
     *  If any validation fails then is throws a business exception.
     * @param model contains data to validate
     */
    public void validateModel(PermissionModel model) {
        if (StringUtil.isEmpty(model.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(model.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }

        if (model.getEnabled() == null) {
            model.setEnabled(true);
        }
    }
}
