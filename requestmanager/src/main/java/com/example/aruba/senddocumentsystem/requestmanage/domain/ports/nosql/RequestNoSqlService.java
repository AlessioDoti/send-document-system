package com.example.aruba.senddocumentsystem.requestmanage.domain.ports.nosql;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;

public interface RequestNoSqlService {
    void persistEvent(RequestDTO dto);
}
