package com.example.aruba.senddocumentsystem.receivermanager.domain.ports.nosql;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;

public interface ReceiverNoSqlService {
    void persistEvent(ReceiverDTO dto);
}
