package com.example.aruba.senddocumentsystem.requestmanage.rest.controller;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanage.domain.ports.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.requestmanage.rest.factory.RequestDTOFactory;
import com.example.aruba.senddocumentsystem.requestmanage.rest.request.InsertRequestReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestDTOFactory factory;

    @Autowired
    private RequestHandler handler;

    @PostMapping
    public ResponseEntity<RequestDTO> insertRequest(@RequestBody InsertRequestReq req){
        var dto = factory.getDTO(req);
        dto = handler.insertRequest(dto);
        return ResponseEntity.ok().body(dto);
    }
}
