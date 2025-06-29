package com.example.aruba.senddocumentsystem.requestmanager.rest.controller;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.port.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.requestmanager.rest.request.InsertRequestReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestHandler requestHandler;

    @Test
    public void whenInvokeRequestsAPICorrectly_ShouldReturn200() throws Exception {
        var body = new InsertRequestReq();
        body.setReceivers(List.of("receiver"));
        body.setDocuments(List.of("doc"));
        body.setDeliveryType("CERTIFIED");
        when(requestHandler.insertRequest(any())).thenReturn(RequestDTO.builder().build());
        mockMvc.perform(post("/requests").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeDeliveriesAPIWithoutArguments_ShouldReturn400() throws Exception {
        var body = new InsertRequestReq();
        body.setReceivers(List.of("receiver"));
        body.setDocuments(List.of("doc"));
        body.setDeliveryType("CERTIFIED");
        when(requestHandler.insertRequest(any())).thenReturn(RequestDTO.builder().build());
        mockMvc.perform(post("/requests")).andExpect(status().isBadRequest());
    }

    @Test
    public void whenInvokeRequestsAPIAndHandlerThrowException_ShouldReturn500() throws Exception {
        var body = new InsertRequestReq();
        body.setReceivers(List.of("receiver"));
        body.setDocuments(List.of("doc"));
        body.setDeliveryType("CERTIFIED");
        when(requestHandler.insertRequest(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(post("/requests").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isInternalServerError());
    }

}
