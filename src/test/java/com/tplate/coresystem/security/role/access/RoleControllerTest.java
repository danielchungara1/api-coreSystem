package com.tplate.coresystem.security.role.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tplate.coresystem.security.role.RoleController;
import com.tplate.coresystem.security.role.RoleInDto;
import com.tplate.coresystem.security.role.RoleOutDto;
import com.tplate.coresystem.security.role.RoleService;
import com.tplate.coresystem.core.BusinessException;
import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.GlobalExceptionHandler;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.dtos.ResponseDto;
import com.tplate.coresystem.core.dtos.ResponseSimpleDto;
import com.tplate.coresystem.util.PrimitiveFactory;
import com.tplate.coresystem.util.RoleFactory;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class RoleControllerTest {

    private MockMvc mvc;

    @Mock
    private RoleService roleService;

    @InjectMocks // Indicates to whom inject mocks.
    private RoleController roleController;

    private JacksonTester<ResponseDto> jsonResponseDto;
    private JacksonTester<ResponseSimpleDto> jsonResponseSimpleDto;
    private JacksonTester<RoleInDto> jsonRoleInDto;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());

        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(roleController)
                .setControllerAdvice(new GlobalExceptionHandler())
                // .addFilters()
                .build();
    }

    @Test
    void canRetrieveResultWhenFindByIdTest() throws Exception {
        // given
        given(roleService.findById(PrimitiveFactory.LONG_ONE))
                .willReturn(
                        RoleFactory.getModelOK()
                );

        // when
        MockHttpServletResponse response = mvc.perform(
                get(Endpoints.ROLES + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(RoleFactory.getModelOK(), RoleOutDto.class)
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
        when(roleService.findById(PrimitiveFactory.LONG_ZERO)).thenThrow(new BusinessException());

        // Exec & Assert
        assertThrows(BusinessException.class, () -> roleController.findById(PrimitiveFactory.LONG_ZERO));
    }

    @Test
    void canRetrieveResulstWhenFindByAllTest() throws Exception {
        // given
        given(roleService.findAll())
                .willReturn(
                        List.of(RoleFactory.getModelOK())
                );

        // when
        MockHttpServletResponse response = mvc.perform(
                get(Endpoints.ROLES)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(List.of(RoleFactory.getModelOK()), RoleOutDto[].class)
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
    void canRetrieveMessageOkWhenDeleteByIdTest() throws Exception {
        // given
        doNothing().when(roleService).deleteById(PrimitiveFactory.LONG_ONE);

        // when
        MockHttpServletResponse response = mvc.perform(
                delete(Endpoints.ROLES + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // expected
        ResponseSimpleDto expected = ResponseSimpleDto.builder()
                .message(ResponseMessages.DELETED)
                .build();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonResponseSimpleDto.write(expected).getJson()
        );

        // log
        log.info(
                ">>> Response: {}",
                response.getContentAsString()
        );
    }

    @Test
    void throwExceptionWhenDeleteByIdTest() throws Exception {
        // when
        doThrow(new BusinessException()).when(roleService).deleteById(PrimitiveFactory.LONG_ZERO);

        // Exec & Assert
        assertThrows(BusinessException.class, () -> roleController.deleteById(PrimitiveFactory.LONG_ZERO));
    }


    @Test
    void canRetrieveResultWhenCreateByDtoTest() throws Exception {
        // given
        when(roleService.createByDtoUsingTemplateMethod(RoleFactory.getDtoInOk())).thenReturn(RoleFactory.getModelOK());

        // when
        MockHttpServletResponse response = mvc.perform(
                post(Endpoints.ROLES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRoleInDto.write(RoleFactory.getDtoInOk()).getJson())
        ).andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.CREATED)
                .data(RoleFactory.getModelOK(), RoleOutDto.class)
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
    void canRetrieveResultWhenUpdateByDtoTest() throws Exception {
        // given
        when(roleService.updateByDtoAndIdUsingTemplateMethod(RoleFactory.getDtoInOk(), PrimitiveFactory.LONG_ONE)).thenReturn(RoleFactory.getModelOK());

        // when
        MockHttpServletResponse response = mvc.perform(
                put(Endpoints.ROLES + "/" + PrimitiveFactory.LONG_ONE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRoleInDto.write(RoleFactory.getDtoInOk()).getJson())
        ).andReturn().getResponse();

        // expected
        ResponseDto expected = ResponseDto.builder()
                .message(ResponseMessages.UPDATED)
                .data(RoleFactory.getModelOK(), RoleOutDto.class)
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