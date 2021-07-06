package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import com.tplate.coresystem.shared.business.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPermission {

    @Autowired
    PermissionRepository repository;

    /**
     * Business Rules:
     *  - Description is required
     *  - Display name is required
     *  - Enabled is optional
     *  If any validation fails is throws a business exception.
     * @param model contains data to validate
     */
    public void validateModel(PermissionModel model) {
        if (StringUtil.isEmpty(model.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(model.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }
    }
}
