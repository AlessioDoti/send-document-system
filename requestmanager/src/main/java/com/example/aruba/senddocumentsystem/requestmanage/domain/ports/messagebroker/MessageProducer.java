package com.example.aruba.senddocumentsystem.requestmanage.domain.ports.messagebroker;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;

public interface MessageProducer {

    void produceMessage(RequestDTO dto);
}
