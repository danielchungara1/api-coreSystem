package com.tplate.coresystem.layers.persistence.repositories;

import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.shared.persistence.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionModel> {

}

