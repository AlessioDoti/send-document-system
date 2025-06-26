package com.example.aruba.senddocumentsystem.receivermanager.persistence.mapper;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.Receiver;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ReceiverMapper {

    public Receiver fromDTO(ReceiverDTO dto) {
        var receiver = new Receiver();
        receiver.setAddress(dto.getAddress());
        receiver.setValid(dto.isValid());
        receiver.setFiscalCode(dto.getReceiverFiscalCode());
        receiver.setUsers(new HashSet<>());

        return receiver;
    }

    public ReceiverDTO toDTO(Receiver entity) {
        return ReceiverDTO.builder()
                .valid(entity.isValid())
                .address(entity.getAddress())
                .receiverFiscalCode(entity.getFiscalCode())
                .build();
    }
}
