package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.shared.business.ParametricValidator;
import com.tplate.coresystem.shared.persistence.ParametricModel;
import org.springframework.stereotype.Component;

@Component
public class PermissionValidator extends ParametricValidator{

    @Override
    protected void validateNameUniqueness(ParametricModel model) {
    }
}
