package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.access.dtos.RoleInDto;
import com.tplate.coresystem.layers.access.dtos.RoleOutDto;
import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.layers.persistence.repositories.RoleRepository;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.business.services.CreatableService;
import com.tplate.coresystem.shared.business.services.DeletableService;
import com.tplate.coresystem.shared.business.services.SearchableService;
import com.tplate.coresystem.shared.business.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService implements
        SearchableService<
                RoleRepository,
                RoleModel>,
        DeletableService<
                RoleRepository,
                RoleModel
                >,
        CreatableService<
                RoleRepository,
                RoleModel,
                RoleInDto
                > {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public RoleRepository getRepository() {
        return this.repository;
    }

    @Override
    public void validateDto(RoleInDto dto) {
        if (StringUtil.isEmpty(dto.getName())) {
            throw new BusinessException("name is required");
        }

        if (StringUtil.isEmpty(dto.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(dto.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }

        if (this.getRepository().existsByName(dto.getName())) {
            throw new BusinessException("name exists");
        }

        dto.getPermissionsId().stream().forEach(idPermission -> {
            if (!this.permissionRepository.existsById(idPermission)) {
                throw new BusinessException("permission id %s not exist".formatted(idPermission));
            }
        });
    }

    @Override
    public RoleModel buildModelByDto(RoleInDto dto) {
        return
                RoleModel.builder()
                        .name(dto.getName())
                        .description(dto.getDescription())
                        .displayName(dto.getDisplayName())
                        .permissions(this.permissionRepository.findAllById(dto.getPermissionsId()))
                        .build();
    }

}
