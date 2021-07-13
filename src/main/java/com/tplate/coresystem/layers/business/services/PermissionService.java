package com.tplate.coresystem.layers.business.services;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.business.validators.PermissionValidator;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Builder
@Slf4j
public class PermissionService {

    @Autowired
    private final PermissionRepository repository;

    @Autowired
    private final PermissionValidator validator;

    /**
     * Goal: Read all permissions.
     *
     * @return all permissions
     */
    @Transactional(rollbackOn = Exception.class)
    public List<PermissionModel> readAll() {
        return this.repository.findAll();
    }

    /**
     * Goal: Update permission by DTO and Id.
     *
     * @param id  of permission.
     * @param dto with updatable fields.
     * @return permission updated
     */
    @Transactional(rollbackOn = Exception.class)
    public PermissionModel updateByDto(Long id, PermissionDto dto) {

        this.validator.validate(dto, id);

        PermissionModel model = this.repository.getById(id);

        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());

        return this.repository.save(model);

    }

}
