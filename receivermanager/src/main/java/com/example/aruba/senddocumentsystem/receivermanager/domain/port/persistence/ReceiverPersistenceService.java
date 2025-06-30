package com.example.aruba.senddocumentsystem.receivermanager.domain.port.persistence;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReceiverPersistenceService {

    ReceiverDTO insertReceiver(ReceiverDTO dto);
    ReceiverDTO updateReceiver(ReceiverDTO dto);
    Page<ReceiverDTO> findUserReceivers(String username, Pageable pageable);
    List<ReceiverDTO> findReceiversFromCodes(List<String> codes, String user);
}
