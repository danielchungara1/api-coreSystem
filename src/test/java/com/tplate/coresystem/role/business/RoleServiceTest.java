package com.tplate.coresystem.role.business;

import com.tplate.coresystem.permission.business.PermissionService;
import com.tplate.coresystem.permission.persistence.PermissionModel;
import com.tplate.coresystem.permission.persistence.PermissionRepository;
import com.tplate.coresystem.role.persistence.RoleModel;
import com.tplate.coresystem.role.persistence.RoleRepository;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.util.PermissionFactory;
import com.tplate.coresystem.util.PrimitiveFactory;
import com.tplate.coresystem.util.RoleFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class RoleServiceTest {

    @Mock
    RoleRepository mockRepository;

    @Mock
    PermissionRepository mockPermissionRepository;

    @InjectMocks // Indicates to whom inject mocks.
    RoleService roleService;

    @Test
    void voidResultWhendeleteByIdTest() {
        // when
        when(mockRepository.existsById(PrimitiveFactory.LONG_ONE)).thenReturn(true);
        doNothing().when(mockRepository).deleteById(eq(PrimitiveFactory.LONG_ONE), Matchers.any(), Matchers.any());

        // exec & assert
        assertThatNoException().isThrownBy(() -> this.roleService.deleteById(PrimitiveFactory.LONG_ONE));

    }

    @Test
    void throwExceptionWhendeleteByIdTest() {
        // when
        when(mockRepository.existsById(PrimitiveFactory.LONG_ONE)).thenReturn(false);

        // exec & assert
        assertThrows(BusinessException.class, () -> this.roleService.deleteById(PrimitiveFactory.LONG_ONE));

    }

    @Test
    void voidResultWhenValidateDtoGeneralTest() {

        // when
        when(mockPermissionRepository.existsById(any())).thenReturn(true);

        // exec & assert
        assertThatNoException().isThrownBy(() -> this.roleService.validateDtoGeneral(RoleFactory.getDtoInOk()));

    }

    @Test
    void voidResultWhenValidateDtoForCreateTest() {

        // when
        when(mockRepository.existsByName(any())).thenReturn(false);

        // exec & assert
        assertThatNoException().isThrownBy(() -> this.roleService.validateDtoForCreate(RoleFactory.getDtoInOk()));

    }

    @Test
    void canRetrieveResultWhenBuildModelByDtoTest() {

        // when
        when(mockPermissionRepository.findAllById(any())).thenReturn(RoleFactory.getModelOK().getPermissions());

        // expected
        RoleModel expected = RoleFactory.getModelOK();


        // exec
        RoleModel result = this.roleService.buildModelByDto(RoleFactory.getDtoInOk());

        // assert
        assertThat(result.getName()).isEqualTo(expected.getName());
        assertThat(result.getDescription()).isEqualTo(expected.getDescription());
        assertThat(result.getDisplayName()).isEqualTo(expected.getDisplayName());

        // log
        log.info(
                ">>> Result: {}",
                result.toString()
        );
    }

    @Test
    void voidResultWhenValidateDtoAndIdForUpdateTest() {

        // when
        when(mockRepository.existsById(any())).thenReturn(true);
        when(mockRepository.existsByNameExcludingId(any(), any())).thenReturn(false);

        // exec & assert
        assertThatNoException().isThrownBy(
                () -> this.roleService.validateDtoAndIdForUpdate(RoleFactory.getDtoInOk(), PrimitiveFactory.LONG_ONE)
        );

    }

    @Test
    void canRetrieveResultWhenMapModelByDtoTest() {

        // when
        when(mockPermissionRepository.findAllById(any())).thenReturn(RoleFactory.getModelOK().getPermissions());

        // expected
        RoleModel expected = RoleFactory.getModelOK();


        // exec
        RoleModel result = this.roleService.mapModelByDto(RoleFactory.getModelOK(), RoleFactory.getDtoInOk());

        // assert
        assertThat(result.getName()).isEqualTo(expected.getName());
        assertThat(result.getDescription()).isEqualTo(expected.getDescription());
        assertThat(result.getDisplayName()).isEqualTo(expected.getDisplayName());

        // log
        log.info(
                ">>> Result: {}",
                result.toString()
        );
    }
}