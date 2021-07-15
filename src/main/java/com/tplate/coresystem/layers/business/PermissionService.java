package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.access.dtos.ParametricDto;
import com.tplate.coresystem.shared.business.services.ParametricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class PermissionService extends ParametricService<PermissionRepository> {

    /**
     * Goal: Update permission by DTO and Id.
     *
     * @param id  of permission.
     * @param dto with updatable fields.
     * @return permission updated
     */
    @Transactional(rollbackOn = Exception.class)
    public PermissionModel updateByDto(Long id, PermissionDto dto) {

        this.validateDtoForUpdate(dto, id);

        PermissionModel model = this.repository.getById(id);

        model.setDescription(dto.getDescription());
        model.setDisplayName(dto.getDisplayName());

        return this.repository.save(model);

    }

    /**
     * Check Validation Rules for Update:
     *  - Description is required
     *  - Display name is required
     *  - Id must exist
     *  If any validation fails is throws a business exception.
     */
    public void validateDtoForUpdate(ParametricDto dto, Long id) {

        super.descriptionIsRequired(dto.getDescription());
        super.displayNameIsRequired(dto.getDisplayName());
        super.idMustExist(id);

    }

    /**
     * Inject repository for testing.
     * @param repository
     */
    public void setRepository(PermissionRepository repository) {
        super.setRepository(repository);
    }

}
