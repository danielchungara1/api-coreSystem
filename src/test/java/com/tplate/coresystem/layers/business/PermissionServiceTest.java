package com.tplate.coresystem.layers.business;

import com.tplate.coresystem.TestContainers;
import com.tplate.coresystem.layers.permission.business.PermissionService;
import com.tplate.coresystem.layers.permission.persistence.PermissionModel;
import com.tplate.coresystem.layers.permission.persistence.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@Slf4j
class PermissionServiceTest extends TestContainers {

    @Autowired
    PermissionService service;

    @Autowired
    PermissionRepository repository;

    @Test
    void readAll_withEmptyRepository() {

        final Integer TOTAL_PERMISSIONS =  service.readEnabled().size();

        log.info(">>> Total of permissions {}", TOTAL_PERMISSIONS);

        assertThat(TOTAL_PERMISSIONS).isEqualTo(0);

    }

    @Test
    void readAll_withOneEntityInserted () {
        // Save test permission
        this.repository.save(
                PermissionModel.builder()
                .name("TEST_PERMISSION")
                .build()
        );

        // Read permissions
        final Integer TOTAL_PERMISSIONS =  service.readEnabled().size();
        log.info(">>> Total of permissions {}", TOTAL_PERMISSIONS);

        // Assert
        assertThat(TOTAL_PERMISSIONS).isEqualTo(1);
    }


}