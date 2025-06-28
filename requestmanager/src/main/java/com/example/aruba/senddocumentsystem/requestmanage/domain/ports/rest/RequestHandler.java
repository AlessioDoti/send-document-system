package com.example.aruba.senddocumentsystem.requestmanage.domain.ports.rest;

import com.example.aruba.senddocumentsystem.requestmanage.domain.dto.RequestDTO;

public interface RequestHandler {

    RequestDTO insertRequest(RequestDTO dto);
}
