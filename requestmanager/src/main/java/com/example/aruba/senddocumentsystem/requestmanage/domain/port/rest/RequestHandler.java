package com.example.aruba.senddocumentsystem.requestmanage.domain.port.rest;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;

public interface RequestHandler {

    RequestDTO insertRequest(RequestDTO dto);
}
