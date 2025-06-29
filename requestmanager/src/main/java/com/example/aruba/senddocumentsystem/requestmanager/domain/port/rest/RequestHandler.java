package com.example.aruba.senddocumentsystem.requestmanager.domain.port.rest;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;

public interface RequestHandler {

    RequestDTO insertRequest(RequestDTO dto);
}
