package com.tplate.coresystem.layers.persistence.repositories;

import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends SearchableRepository<PermissionModel> {

}

