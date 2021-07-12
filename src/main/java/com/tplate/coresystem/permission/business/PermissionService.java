package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.access.PermissionDtoIn;
import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Builder
@Slf4j
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Autowired
    private PermissionValidator validator;

    /**
     * Goal: read all permissions
     *
     * @return permissions
     */
    @Transactional(rollbackOn = Exception.class)
    public List<PermissionModel> readAll() {
        return this.repository.findAllByEnabledTrue();
    }

    /**
     * Goal: Update a permission by DTO
     *
     * @param id  of permission to update
     * @param dto contains fields to update
     * @return permission updated
     */
    @Transactional(rollbackOn = Exception.class)
    public PermissionModel updateByDto(Long id, PermissionDtoIn dto) {

        PermissionModel model = this.getModelById(id);

        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());

        this.validator.validateModel(model);

        return this.repository.save(model);

    }

    /**
     * Goal: fetch model by its primary key (ID)
     * If not exist throws business exception
     *
     * @param id primary key
     * @return model
     */
    @Transactional(rollbackOn = Exception.class)
    public PermissionModel getModelById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new BusinessException("permission id " + id + " does not exists."));
    }


    /**
     * Get permissions from ids
     *
     * @param permissionIds
     * @return list of permissions or null if not present.
     */
    @Transactional(rollbackOn = Exception.class)
    public List<PermissionModel> getModels(Optional<List<Long>> permissionIds) {
        return permissionIds.isPresent()
                ?
                permissionIds
                        .get()
                        .stream()
                        .map(permissionId -> this.getModelById(permissionId))
                        .collect(Collectors.toList())
                :
                null;

    }
}
