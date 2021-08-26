package com.tplate.coresystem.security.permission;

import com.tplate.coresystem.core.repositories.SearchableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends SearchableRepository<PermissionModel> {

}

