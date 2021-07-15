package com.tplate.coresystem.layers.persistence.repositories;

import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.shared.persistence.repositories.ParametricRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ParametricRepository<RoleModel> {

}

