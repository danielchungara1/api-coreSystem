package com.tplate.coresystem.role.business;

import com.tplate.coresystem.role.persistence.RoleRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import com.tplate.coresystem.shared.business.ParametricValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator extends ParametricValidator{

    @Autowired
    RoleRepository repository;

    @Override
    protected void validateNameUniqueness(String name) {
        if (this.repository.existsByName(name)) {
            throw new BusinessException("name exists");
        }
    }
}
