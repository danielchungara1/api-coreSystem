package com.tplate.coresystem.shared.business;

import com.tplate.coresystem.shared.persistence.ParametricModel;
import org.springframework.stereotype.Component;

@Component
public abstract class ParametricValidator {

    /**
     * Business Rules:
     *  - Description is required
     *  - Display name is required
     *  - Name es required & unique
     *  If any validation fails is throws a business exception.
     * @param model contains data to validate
     */
    public void validateModel(ParametricModel model) {

        if (StringUtil.isEmpty(model.getName())) {
            throw new BusinessException("name is required");
        }

        if (StringUtil.isEmpty(model.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(model.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }

        this.validateNameUniqueness(model);
    }

    protected abstract void validateNameUniqueness(ParametricModel model);

}
