package com.tplate.coresystem.permission.persistence;

import com.tplate.coresystem.shared.repositories.SearchableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends SearchableRepository<PermissionModel> {

}

