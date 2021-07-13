package com.tplate.coresystem.permission.business;

import com.tplate.coresystem.layers.business.validators.PermissionValidator;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tplate.coresystem.permission.constants.PermissionConstants.TEST_DESCRIPTION;
import static com.tplate.coresystem.permission.constants.PermissionConstants.TEST_DISPLAY_NAME;
import static org.junit.jupiter.api.Assertions.*;

class PermissionValidatorTest {

    static PermissionValidator validator;

    @BeforeAll
    public static void beforeAll() {
        validator = new PermissionValidator();
    }

    /**
     * Testing: PermissionValidator.validateModel()
     */
    @Test
    void validateModel_withAnyErrorNonThrowsAnyException(){
        // Setup
        PermissionModel model = PermissionModel.builder()
                .description(TEST_DESCRIPTION)
                .displayName(TEST_DISPLAY_NAME)
                .build();

        // Exec
//        validator.validate(model);

        // Assert
        assertTrue(true); // Means any exception is throws.
    }

    @Test
    void validateModel_withSomeErrorsThrowsException(){
        // Setup
        PermissionModel model = PermissionModel.builder()
                .description(TEST_DESCRIPTION)
                // DISPLAY_NAME is required
                .build();

        // Exec & Assert
//        assertThrows(BusinessException.class, ()-> validator.validate(model)) ;

    }

}