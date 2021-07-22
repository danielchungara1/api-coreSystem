package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.layers.access.dtos.PermissionDto;
import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.tplate.coresystem.permission.constants.PermissionConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    PermissionService permissionService;

    @Mock
    PermissionRepository mockRepository;

    @BeforeEach
    public void beforeEach() {
        this.permissionService = new PermissionService();
        this.permissionService.setRepository(mockRepository);
    }

    @Test
    void validateModel_withNonErrorsNonThrowsException() {

        // Mocking
        when(mockRepository.existsById(TEST_ID)).thenReturn(true);

        // Setup
        PermissionDto dto = PermissionDto.builder()
                .description(TEST_DESCRIPTION)
                .displayName(TEST_DISPLAY_NAME)
                .build();

        // Exec
        permissionService.validateDtoForUpdate(dto, TEST_ID);

        // Assert
        assertTrue(false); // It means that no exception was thrown.
    }

    @Test
    void validateModel_withSomeErrorsThrowsException() {

        // Setup
        PermissionDto dto = PermissionDto.builder()
                .description(TEST_DESCRIPTION)
                // DISPLAY_NAME is required
                .build();

        // Exec & Assert
        assertThrows(BusinessException.class, () -> permissionService.validateDtoForUpdate(dto, TEST_ID));

    }

    @Test
    void getModelById_withExistingIdReturnsModel() {

        // Setup
        when(mockRepository.getById(TEST_ID)).thenReturn(
                PermissionModel.builder()
                        .id(TEST_ID)
                        .name(TEST_NAME)
                        .build()
        );

        // Exec
        PermissionModel model = permissionService.getById(TEST_ID);

        // Assert
        assertNotNull(model);
        assertEquals(TEST_ID, model.getId());

    }

    @Test
    void getModelById_withNonExistingIdThrowsException() {

        // Setup
        when(mockRepository.existsById(UNKNOWN_ID)).thenReturn(false);

        // Exec & Assert
        assertThrows(BusinessException.class, () -> permissionService.idMustExist(UNKNOWN_ID));

    }

}