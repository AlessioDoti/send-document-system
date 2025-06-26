package com.example.aruba.senddocumentsystem.receivermanager.domain.ports.rest;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestHandler {

    ReceiverDTO insertReceiver(ReceiverDTO dto);

    ReceiverDTO updateReceiver(ReceiverDTO dto);

    Page<ReceiverDTO> getUserReceivers(String username, Pageable pageable);
}
