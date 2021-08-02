package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.access.dtos.RoleDto;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.business.services.BaseService;
import com.tplate.coresystem.shared.business.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService extends BaseService<RoleRepository, RoleModel, RoleDto> {

    @Autowired
    protected PermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(RoleDto dto) {

        /*********************************************************
         * Validations
         ********************************************************/
        if (StringUtil.isEmpty(dto.getName())) {
            throw new BusinessException("name is required");
        }

        if (StringUtil.isEmpty(dto.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(dto.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }

        if (this.repository.existsByName(dto.getName())) {
            throw new BusinessException("name exists");
        }

        dto.getPermissionIds().stream().forEach(idPermission -> {
            if (!this.permissionRepository.existsById(idPermission)) {
                throw new BusinessException("permission id %s not exist".formatted(idPermission));
            }
        });

        /*********************************************************
         * Save
         ********************************************************/
        super.repository.save(
                RoleModel.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .displayName(dto.getDisplayName())
                        .permissions(this.permissionRepository.findAllById(dto.getPermissionIds()))
                        .build()
        );

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void update(RoleDto dto, Long id) {
        /*********************************************************
         * Validations
         ********************************************************/
        if (!this.repository.existsById(id)) {
            throw new BusinessException("role id %s not exist".formatted(id));
        }

        if (StringUtil.isEmpty(dto.getName())) {
            throw new BusinessException("name is required");
        }

        if (StringUtil.isEmpty(dto.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(dto.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }

        if (this.repository.existsByNameExcludingId(dto.getName(), id)) {
            throw new BusinessException("name exists");
        }

        dto.getPermissionIds().stream().forEach(idPermission -> {
            if (!this.permissionRepository.existsById(idPermission)) {
                throw new BusinessException("permission id %s not exist".formatted(idPermission));
            }
        });

        /*********************************************************
         * Save
         ********************************************************/
        RoleModel model = repository.getById(id);
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());
        model.setPermissions(this.permissionRepository.findAllById(dto.getPermissionIds()));

        super.repository.save( model );
    }

}
