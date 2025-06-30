package com.example.aruba.senddocumentsystem.receivermanager.rest.controller;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverAlreadyExistsException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverNotFoundException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.port.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.InsertReceiverRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.UpdateReceiverRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class ReceiverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestHandler requestHandler;

    @Test
    public void whenInvokeReceiversAPICorrectly_ShouldReturn200() throws Exception {
        var body = new InsertReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.insertReceiver(any())).thenReturn(ReceiverDTO.builder().build());
        mockMvc.perform(post("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeReceiversAPIUnCorrectly_ShouldReturn400() throws Exception {
        when(requestHandler.insertReceiver(any())).thenReturn(ReceiverDTO.builder().build());
        mockMvc.perform(post("/receivers")).andExpect(status().isBadRequest());
    }

    @Test
    public void whenInvokeReceiversAPICorrectlyAndHandlerThrowsReceiverAlreadyExistsException_ShouldReturn403() throws Exception {
        var body = new InsertReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.insertReceiver(any())).thenThrow(ReceiverAlreadyExistsException.class);
        mockMvc.perform(post("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isForbidden());
    }

    @Test
    public void whenInvokeReceiversAPIAndHandlerThrowException_ShouldReturn500() throws Exception {
        var body = new InsertReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.insertReceiver(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(post("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isInternalServerError());
    }

    @Test
    public void whenInvokeUpdateReceiversAPICorrectly_ShouldReturn200() throws Exception {
        var body = new UpdateReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.updateReceiver(any())).thenReturn(ReceiverDTO.builder().build());
        mockMvc.perform(patch("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeUpdateReceiversAPICorrectlyAndHandlerThrowReceiverNotFoundException_ShouldReturn404() throws Exception {
        var body = new UpdateReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.updateReceiver(any())).thenThrow(ReceiverNotFoundException.class);
        mockMvc.perform(patch("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isNotFound());
    }

    @Test
    public void whenInvokeUpdateReceiversAPIAndHandlerThrowException_ShouldReturn500() throws Exception {
        var body = new UpdateReceiverRequest();
        body.setAddress("address");
        body.setReceiverKey("key");
        body.setUsername("user");
        when(requestHandler.updateReceiver(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(patch("/receivers").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(body))).andExpect(status().isInternalServerError());
    }

    @Test
    public void whenInvokeGetUserReceiversAPICorrectly_ShouldReturn200() throws Exception {
        when(requestHandler.getUserReceivers(any(), any())).thenReturn(Page.empty());
        mockMvc.perform(get("/receivers?username=user")).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeGetUserReceiversAPIUnCorrectly_ShouldReturn400() throws Exception {
        when(requestHandler.getUserReceivers(any(), any())).thenReturn(Page.empty());
        mockMvc.perform(get("/receivers")).andExpect(status().isBadRequest());
    }

    @Test
    public void whenInvokeGetUserReceiversAPICorrectlyAndHandlerThrowsException_ShouldReturn500() throws Exception {
        when(requestHandler.getUserReceivers(any(), any())).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/receivers?username=user")).andExpect(status().isInternalServerError());
    }

    @Test
    public void whenInvokeGetReceiversFromCodesAPICorrectly_ShouldReturn200() throws Exception {
        when(requestHandler.getReceiversFromCodes(any(), any())).thenReturn(List.of());
        mockMvc.perform(get("/receivers/internal?user=user&codes=code1")).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeGetReceiversFromCodesAPIUnCorrectly_ShouldReturn400() throws Exception {
        when(requestHandler.getReceiversFromCodes(any(), any())).thenReturn(List.of());
        mockMvc.perform(get("/receivers/internal")).andExpect(status().isBadRequest());
    }

    @Test
    public void whenInvokeGetReceiversFromCodesAPICorrectlyAndHandlerThrowsException_ShouldReturn500() throws Exception {
        when(requestHandler.getReceiversFromCodes(any(), any())).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/receivers/internal?user=user&codes=code1")).andExpect(status().isInternalServerError());
    }

}
