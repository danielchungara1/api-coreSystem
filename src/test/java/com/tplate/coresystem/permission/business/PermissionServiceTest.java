package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.util.PermissionFactory;
import com.tplate.coresystem.shared.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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
    void canRetrieveResultWhenFindByIdTest() {

        // when
        when(mockRepository.findById(PermissionFactory.LONG_ONE)).thenReturn(
                Optional.ofNullable(PermissionFactory.MODEL_OK)
        );

        // exec
        PermissionModel result = this.permissionService.findById(PermissionFactory.LONG_ONE);

        // expected
        PermissionModel expected = PermissionFactory.MODEL_OK;

        // Assert
        assertThat(result).isNotNull();
        assertThat(expected).isEqualTo(result);
        assertThat(expected.getName()).isEqualTo(result.getName());


        // log
        log.info(
                ">>> Result: {}",
                result.toString()
        );


    }

    @Test
    void throwExceptionWhenFindByIdTest() {

        // when
        when(mockRepository.findById(PermissionFactory.LONG_ZERO)).thenReturn(Optional.ofNullable(null));

        // Exec & Assert
        assertThrows(BusinessException.class, () -> permissionService.findById(PermissionFactory.LONG_ZERO));

    }

    @Test
    void canRetrieveResultsWhenFindAllTest() {

        // when
        when(mockRepository.findAll()).thenReturn(
                List.of(PermissionFactory.MODEL_OK)
        );

        // exec
        List<PermissionModel> result = this.permissionService.findAll();

        // expected
        List<PermissionModel> expected = List.of(PermissionFactory.MODEL_OK);

        // Assert
        assertThat(result).isNotNull();
        assertThat(expected).isEqualTo(result);

        // log
        log.info(
                ">>> Result: {}",
                result.toString()
        );

    }



}