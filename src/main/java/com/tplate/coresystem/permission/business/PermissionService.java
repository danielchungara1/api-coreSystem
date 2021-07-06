package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.access.PermissionDtoIn;
import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Autowired
    private ValidatorPermission validator;

    /**
     * Goal: read all enabled permissions
     * @return enabled permissions
     */
    @Transactional(rollbackOn = Exception.class)
    public List<PermissionModel> readEnabled() {
        return this.repository.findAllByEnabledTrue();
    }

    /**
     * Goal: Update a permission by DTO
     * @param id of permission to update
     * @param dto contains fields to update
     * @return permission updated
     */

    @Transactional(rollbackOn = Exception.class)
    public PermissionModel updateByDto(Long id, PermissionDtoIn dto) {
        PermissionModel model = this.getModelById(id);

        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());
        model.setEnabled(dto.getEnabled());

        this.validator.validateModel(model);

        return this.repository.save(model);

    }

    /**
     * Goal: fetch model by its primary key (ID)
     * If not exist throws business exception
     * @param id primary key
     * @return model
     */
    @Transactional
    public PermissionModel getModelById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BusinessException("id not exists."));
    }

}
