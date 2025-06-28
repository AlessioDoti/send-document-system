package com.example.aruba.senddocumentsystem.receivermanager.domain.port.messagebroker;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;

public interface MessageProducer {

    void produceMessage(ReceiverDTO receiverDTO);
}
