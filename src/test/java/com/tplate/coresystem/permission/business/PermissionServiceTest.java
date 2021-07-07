package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.access.PermissionDtoIn;
import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.shared.business.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.tplate.coresystem.permission.constants.PermissionConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {

    PermissionService service;

    @Mock
    PermissionRepository mockRepository;

    @BeforeEach
    public void before() {
        // Setup service
        service = PermissionService.builder()
                .repository(mockRepository)
                .build();
    }

    /**
     * Testing: PermissionService.getModelById()
     */
    @Test
    void getModelById_withExistingIdReturnsModel() {

        // Setup
        when(mockRepository.findById(TEST_ID)).thenReturn(Optional.of(
                PermissionModel.builder()
                        .id(TEST_ID)
                        .name(TEST_NAME)
                        .build()
        ));

        // Exec
        PermissionModel model = service.getModelById(TEST_ID);

        // Assert
        assertNotNull(model);
        assertEquals(TEST_ID, model.getId());

    }

    @Test
    void getModelById_withNonExistingIdThrowsException() {

        // Setup
        when(mockRepository.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

        // Exec & Assert
        assertThrows(BusinessException.class, () -> service.getModelById(UNKNOWN_ID));

    }

}