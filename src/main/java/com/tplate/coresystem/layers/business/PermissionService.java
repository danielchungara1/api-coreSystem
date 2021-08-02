package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.business.services.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PermissionService extends BaseService <PermissionRepository, PermissionModel, PermissionDto> {


    @Override
    public void create(PermissionDto dto) {
        throw new BusinessException("Create permission not permitted.");
    }

    @Override
    public void update(PermissionDto dto, Long id) {
        throw new BusinessException("Update permission not permitted.");
    }

    @Override
    public void deleteById(Long id) {
        throw new BusinessException("Delete permission not permitted.");
    }
}
