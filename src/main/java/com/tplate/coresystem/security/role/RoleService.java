package com.tplate.coresystem.security.role;

import com.tplate.coresystem.security.permission.PermissionRepository;
import com.tplate.coresystem.core.BusinessException;
import com.tplate.coresystem.core.StringUtil;
import com.tplate.coresystem.core.services.CreatableService;
import com.tplate.coresystem.core.services.DeletableService;
import com.tplate.coresystem.core.services.SearchableService;
import com.tplate.coresystem.core.services.UpdatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                >,
        UpdatableService<
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
    public void validateDtoGeneral(RoleInDto dto) {

        if (StringUtil.isEmpty(dto.getName())) {
            throw new BusinessException("name is required");
        }

        if (StringUtil.isEmpty(dto.getDescription())) {
            throw new BusinessException("description is required");
        }

        if (StringUtil.isEmpty(dto.getDisplayName())) {
            throw new BusinessException("displayName is required");
        }


        dto.getPermissionsId().stream().forEach(idPermission -> {
            if (!this.permissionRepository.existsById(idPermission)) {
                throw new BusinessException("permission id %s not exist".formatted(idPermission));
            }
        });

    }

    @Override
    public void validateDtoForCreate(RoleInDto dto) {
        if (this.getRepository().existsByName(dto.getName())) {
            throw new BusinessException("name exists");
        }
    }

    @Override
    public void validateDtoAndIdForUpdate(RoleInDto dto, Long id) {

        if (!this.getRepository().existsById(id)) {
            throw new BusinessException("Role id %s not exist".formatted(id));
        }

        if (this.getRepository().existsByNameExcludingId(dto.getName(), id)) {
            throw new BusinessException("name exists");
        }
    }

    @Override
    public RoleModel buildModelByDto(RoleInDto dto) {
        return
                this.mapModelByDto(new RoleModel(), dto);
    }

    @Override
    public RoleModel mapModelByDto(RoleModel model, RoleInDto dto) {

        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());
        model.setPermissions(this.permissionRepository.findAllById(dto.getPermissionsId()));

        return model;
    }

}
