package com.example.aruba.senddocumentsystem.receivermanager.persistence.service;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.InvalidUsernameException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverAlreadyExistsException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverNotFoundException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.ports.persistence.ReceiverPersistenceService;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.Receiver;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.User;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.mapper.ReceiverMapper;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.repository.ReceiverRepository;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiverPersistenceServiceImpl implements ReceiverPersistenceService {

    @Autowired
    private final ReceiverRepository receiverRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ReceiverMapper mapper;

    @Override
    public ReceiverDTO insertReceiver(ReceiverDTO dto) {
        var receiver = receiverRepository.findByFiscalCode(dto.getReceiverFiscalCode())
                .orElse(null);

        if(Objects.isNull(receiver)){
            log.info("Receiver not existing, it will be created");
            receiver = mapper.fromDTO(dto);
        } else{
            log.info("Receiver already exists with id {}", receiver.getId().toString());
            checkUsers(receiver.getUsers(), dto.getUsername());
        }
        addUser(receiver, dto.getUsername());

        return mapper.toDTO(receiverRepository.save(receiver));

    }

    @Override
    public ReceiverDTO updateReceiver(ReceiverDTO dto) {
        var receiver = receiverRepository.findByFiscalCode(dto.getReceiverFiscalCode())
                .orElseThrow(() -> new ReceiverNotFoundException("The receiver doesn't exist"));
        var updated = mapper.fromDTO(dto);

        updated.setId(receiver.getId());
        updated.setUsers(receiver.getUsers());

        return mapper.toDTO(receiverRepository.save(updated));
    }

    @Override
    public Page<ReceiverDTO> findUserReceivers(String username, Pageable pageable) {
        return receiverRepository.findByUsers_Username(username, pageable).map(mapper::toDTO);
    }

    private void addUser(Receiver receiver, String username){
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUsernameException("Username does not exist!"));

        receiver.getUsers().add(user);
        log.info("User {} added to users list", username);
    }

    private void checkUsers(Set<User> users, String username){
        log.info("Checking user list");
        var user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if(Objects.nonNull(user)){
            throw new ReceiverAlreadyExistsException("Receiver already exists for this user!!");
        }
    }

}
