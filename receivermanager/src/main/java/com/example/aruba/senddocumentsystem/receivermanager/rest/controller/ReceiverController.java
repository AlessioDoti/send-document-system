package com.example.aruba.senddocumentsystem.receivermanager.rest.controller;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest.RequestHandler;
import com.example.aruba.senddocumentsystem.receivermanager.rest.factory.ReceiverDTOFactory;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.InsertReceiverRequest;
import com.example.aruba.senddocumentsystem.receivermanager.rest.request.impl.UpdateReceiverRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/receivers")
public class ReceiverController {

    @Autowired
    private final ReceiverDTOFactory factory;

    @Autowired
    private final RequestHandler handler;

    @PostMapping
    public ResponseEntity<ReceiverDTO> insertReceiver(@RequestBody InsertReceiverRequest req){
        var dto = factory.getDTO(req);
        var ret = handler.insertReceiver(dto);
        return ResponseEntity.ok(ret);
    }

    @PatchMapping
    public ResponseEntity<ReceiverDTO> updateReceiver(@RequestBody UpdateReceiverRequest req){
        var dto = factory.getDTO(req);
        var ret = handler.updateReceiver(dto);
        return ResponseEntity.ok(ret);
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ReceiverDTO>>> getUserReceivers(
            @RequestParam("username") String username,
            Pageable pageable,
            PagedResourcesAssembler<ReceiverDTO> assembler) {

        Page<ReceiverDTO> page = handler.getUserReceivers(username, pageable);
        return ResponseEntity.ok(assembler.toModel(page));
    }
}
