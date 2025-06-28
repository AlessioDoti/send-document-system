package com.example.aruba.senddocumentsystem.deliverytracker.rest.controller;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.rest.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private final RequestHandler requestHandler;

    @GetMapping
    public ResponseEntity<DeliveryDTO> getDelivery(@RequestParam("username") String username, @RequestParam("traceparent") String traceParent){
        var ret = requestHandler.findByUsernameAndTraceParent(username, traceParent);
        return ResponseEntity.ok(ret);
    }
}
