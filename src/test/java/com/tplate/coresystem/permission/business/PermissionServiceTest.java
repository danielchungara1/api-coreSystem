package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.layers.persistence.repositories.PermissionRepository;
import com.tplate.coresystem.permission.shared.PermissionFactory;
import com.tplate.coresystem.shared.Constants;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@Slf4j
class PermissionServiceTest {

    @Mock
    PermissionRepository mockRepository;

    @InjectMocks // Indicates to whom inject mocks.
    PermissionService permissionService;

    @Test
    void getModelById_withExistingIdReturnsModel() {

        // when
        when(mockRepository.findById(Constants.LONG_ONE)).thenReturn(
                Optional.ofNullable(PermissionFactory.MODEL_OK)
        );

        // exec
        PermissionModel actual = this.permissionService.findById(Constants.LONG_ONE);

        // expected
        PermissionModel expected = PermissionFactory.MODEL_OK;

        // Assert
        assertThat(actual).isNotNull();
        assertThat(expected).isEqualTo(actual);
        assertThat(expected.getName()).isEqualTo(actual.getName());


        // log
        log.info(
                ">>> Actual: {}",
                actual.toString()
        );


    }

    @Test
    void findById_withNonExistingIdThrowsException() {

        // when
        when(mockRepository.findById(Constants.LONG_ZERO)).thenReturn(Optional.ofNullable(null));

        // Exec & Assert
        assertThrows(BusinessException.class, () -> permissionService.findById(Constants.LONG_ZERO));


    }

}