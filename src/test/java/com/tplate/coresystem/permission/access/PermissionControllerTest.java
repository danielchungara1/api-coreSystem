package com.tplate.coresystem.permission.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tplate.coresystem.layers.access.controllers.PermissionController;
import com.tplate.coresystem.layers.access.dtos.PermissionOutDto;
import com.tplate.coresystem.layers.business.PermissionService;
import com.tplate.coresystem.layers.persistence.models.PermissionModel;
import com.tplate.coresystem.permission.shared.PermissionFactory;
import com.tplate.coresystem.shared.access.Endpoints;
import com.tplate.coresystem.shared.access.GlobalExceptionHandler;
import com.tplate.coresystem.shared.access.Messages;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
@Slf4j
class PermissionControllerTest {

    private MockMvc mvc;

    @Mock
    private PermissionService permissionService;

    @InjectMocks // Indicates to whom inject mocks.
    private PermissionController permissionController;

    private JacksonTester<ResponseDto> jsonResponseDto;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());

        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(permissionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                // .addFilters()
                .build();
    }

    @Test
    void canRetrieveEmptyResultsWhenFindAllPermissionsAreInvoked() throws Exception {

        // given
        given(permissionService.findAll())
                .willReturn(new ArrayList<>());

        // when
        MockHttpServletResponse response = mvc.perform(
                        get(Endpoints.PERMISSIONS)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(new ArrayList<PermissionModel>(), PermissionOutDto[].class)
                .build();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponseDto.write(expected).getJson()
        );

        // log
        log.info(
                ">>> Response: {}",
                response.getContentAsString()
        );

    }

    @Test
    void canRetrieveOneResultWhenFindAllPermissionsAreInvoked() throws Exception {
        // given
        given(permissionService.findAll())
                .willReturn(
                        List.of(PermissionFactory.MODEL_OK)
                );
        // when
        MockHttpServletResponse response = mvc.perform(
                        get(Endpoints.PERMISSIONS)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(List.of(PermissionFactory.MODEL_OK), PermissionOutDto[].class)
                .build();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponseDto.write(expected).getJson()
        );

        // log
        log.info(
                ">>> Response: {}",
                response.getContentAsString()
        );
    }

}