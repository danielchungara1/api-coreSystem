package com.tplate.coresystem.security.permission.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tplate.coresystem.security.permission.PermissionController;
import com.tplate.coresystem.security.permission.PermissionOutDto;
import com.tplate.coresystem.security.permission.PermissionService;
import com.tplate.coresystem.security.permission.PermissionModel;
import com.tplate.coresystem.util.PermissionFactory;
import com.tplate.coresystem.core.*;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.dtos.ResponseDto;
import com.tplate.coresystem.util.PrimitiveFactory;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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
    void canRetrieveEmptyResultsWhenFindAllTest() throws Exception {

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
                .message(ResponseMessages.FETCHED)
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
    void canRetrieveResultsWhenFindAllTest() throws Exception {
        // given
        given(permissionService.findAll())
                .willReturn(
                        List.of(PermissionFactory.getModelOK())
                );
        // when
        MockHttpServletResponse response = mvc.perform(
                get(Endpoints.PERMISSIONS)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(List.of(PermissionFactory.getModelOK()), PermissionOutDto[].class)
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
    void canRetrieveResultWhenFindByIdTest() throws Exception {
        // given
        given(permissionService.findById(PrimitiveFactory.LONG_ONE))
                .willReturn(
                        PermissionFactory.getModelOK()
                );
        // when
        MockHttpServletResponse response = mvc.perform(
                get(Endpoints.PERMISSIONS + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(PermissionFactory.getModelOK(), PermissionOutDto.class)
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
    void throwExceptionWhenFindByIdTest() throws Exception {
        // when
        when(permissionService.findById(PrimitiveFactory.LONG_ZERO)).thenThrow(new BusinessException());

        // Exec & Assert
        assertThrows(BusinessException.class, () -> permissionController.findById(PrimitiveFactory.LONG_ZERO));
    }
}