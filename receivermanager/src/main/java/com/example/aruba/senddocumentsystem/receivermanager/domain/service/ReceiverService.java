package com.example.aruba.senddocumentsystem.receivermanager.domain.service;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.persistence.ReceiverPersistenceService;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReceiverService extends ValidatingService<ReceiverDTO> {

    @Autowired
    private final ReceiverPersistenceService receiverPersistenceService;

    public ReceiverService(Validator validator, ReceiverPersistenceService service) {
        super(validator);
        this.receiverPersistenceService = service;
    }

    public ReceiverDTO insertReceiver(ReceiverDTO dto){
        validate(dto);
        checkEmail(dto.getAddress());
        checkUsername(dto.getUsername());
        return receiverPersistenceService.insertReceiver(dto);
    }

    public ReceiverDTO updateReceiver(ReceiverDTO dto){
        validate(dto);
        return receiverPersistenceService.updateReceiver(dto);
    }

    public Page<ReceiverDTO> findUserReceivers(String username, Pageable pageable){
        checkUsername(username);
        return receiverPersistenceService.findUserReceivers(username, pageable);
    }

    private void checkEmail(String email){
        log.debug("Checking email");
        if(Strings.isBlank(email)){
            throw new IllegalArgumentException("Email can't be Blank!!");
        }
    }

    private void checkUsername(String username){
        log.debug("Checking username");
        if(Strings.isBlank(username)){
            throw new IllegalArgumentException("Username can't be Blank!!");
        }
    }
}
