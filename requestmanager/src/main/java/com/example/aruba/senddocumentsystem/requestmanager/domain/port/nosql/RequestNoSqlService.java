package com.example.aruba.senddocumentsystem.requestmanager.domain.port.nosql;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;

public interface RequestNoSqlService {
    void persistEvent(RequestDTO dto, String error);
}
