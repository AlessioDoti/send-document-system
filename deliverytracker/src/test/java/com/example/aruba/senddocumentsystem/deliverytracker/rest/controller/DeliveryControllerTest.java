package com.example.aruba.senddocumentsystem.deliverytracker.rest.controller;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.exception.DeliveryNotFoundException;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.rest.RequestHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RequestHandler requestHandler;

    @Test
    public void whenInvokeDeliveriesAPICorrectly_ShouldReturn200() throws Exception {
        when(requestHandler.findByUsernameAndTraceParent(anyString(), anyString())).thenReturn(DeliveryDTO.builder().build());
        mockMvc.perform(get("/deliveries?username=user&traceparent=tp")).andExpect(status().isOk());
    }

    @Test
    public void whenInvokeDeliveriesAPICorrectlyAndHandlerThrowNotFound_ShouldReturn404() throws Exception {
        when(requestHandler.findByUsernameAndTraceParent(anyString(), anyString())).thenThrow(DeliveryNotFoundException.class);
        mockMvc.perform(get("/deliveries?username=user&traceparent=tp")).andExpect(status().isNotFound());
    }

    @Test
    public void whenInvokeDeliveriesAPICorrectlyAndHandlerThrowException_ShouldReturn500() throws Exception {
        when(requestHandler.findByUsernameAndTraceParent(anyString(), anyString())).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/deliveries?username=user&traceparent=tp")).andExpect(status().is5xxServerError());
    }

    @Test
    public void whenInvokeDeliveriesAPINotCorrectlyAndHandlerThrowException_ShouldReturn400() throws Exception {
        mockMvc.perform(get("/deliveries")).andExpect(status().isBadRequest());
    }
}
