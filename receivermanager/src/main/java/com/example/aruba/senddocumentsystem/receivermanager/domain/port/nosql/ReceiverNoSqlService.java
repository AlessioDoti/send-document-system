package com.example.aruba.senddocumentsystem.receivermanager.domain.port.nosql;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;

public interface ReceiverNoSqlService {
    void persistEvent(ReceiverDTO dto, String error);
}
