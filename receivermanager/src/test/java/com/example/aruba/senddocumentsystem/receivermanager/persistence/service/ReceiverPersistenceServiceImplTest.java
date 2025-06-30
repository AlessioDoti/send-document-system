package com.example.aruba.senddocumentsystem.receivermanager.persistence.service;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.InvalidUsernameException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverAlreadyExistsException;
import com.example.aruba.senddocumentsystem.receivermanager.domain.exception.ReceiverNotFoundException;
import com.example.aruba.senddocumentsystem.receivermanager.mongo.repository.ReceiverMongoRepository;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.repository.ReceiverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "com.example.aruba.senddocumentsystem.receivermanager.persistence")
public class ReceiverPersistenceServiceImplTest {

    @MockitoBean
    private ReceiverMongoRepository mongoRepository;

    @Autowired
    private ReceiverPersistenceServiceImpl service;

    @Autowired
    private ReceiverRepository repository;


    @Test
    public void whenInvokeInsertReceiverWithAValidDTO_ShouldPersistInsertTheReceiver(){
        var dto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("user1")
                .build();

        service.insertReceiver(dto);

        var persisted = repository.findAll().stream().findFirst().orElse(null);

        assertNotNull(persisted);

    }

    @Test
    public void whenInvokeInsertReceiverWithAValidDTOAndExists_ShouldThrowReceiverAlreadyExistsException(){
        var dto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("user2")
                .build();

        service.insertReceiver(dto);

        assertThrows(ReceiverAlreadyExistsException.class, () -> service.insertReceiver(dto));
    }

    @Test
    public void whenInvokeInsertReceiverWithAnInValidUsername_ShouldThrowInvalidUsernameException(){
        var dto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("username")
                .build();

        assertThrows(InvalidUsernameException.class, () -> service.insertReceiver(dto));
    }

    @Test
    public void whenInvokeUpdateReceiverWithAValidDTO_ShouldUpdateTheReceiver(){
        var insertDto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("user1")
                .build();

        service.insertReceiver(insertDto);

        var updateDto = ReceiverDTO.builder()
                .valid(true)
                .address("address2")
                .receiverFiscalCode("fiscalCode")
                .build();

        updateDto = service.updateReceiver(updateDto);
        assertNotNull(updateDto);

    }

    @Test
    public void whenInvokeUpdateReceiverWithANonExistingReceiver_ShouldThrowReceiverNotFoundException(){
        var updateDto = ReceiverDTO.builder()
                .valid(true)
                .address("address2")
                .receiverFiscalCode("fiscalCode")
                .build();

        assertThrows(ReceiverNotFoundException.class, () -> service.updateReceiver(updateDto));

    }

    @Test
    public void whenInvokeFindUserReceivers_ShouldReturnAPageOfUserReceivers(){
        var insertDto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("user1")
                .build();

        service.insertReceiver(insertDto);

        insertDto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode1")
                .address("address1")
                .username("user1")
                .build();

        service.insertReceiver(insertDto);

        var receivers = service.findUserReceivers("user1", Pageable.ofSize(20));
        assertEquals(2, receivers.getTotalElements());
    }

    @Test
    public void whenInvokeFindUserReceiversWithAUserThatHasntAnyReceiver_ShouldReturnAnEmptyPage(){
        var receivers = service.findUserReceivers("user1", Pageable.ofSize(20));
        assertEquals(0, receivers.getTotalElements());
    }

    @Test
    public void whenInvokeFindReceiversFromCodes_ShouldReturnAListOfUserReceivers(){
        var insertDto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode")
                .address("address")
                .username("user1")
                .build();

        service.insertReceiver(insertDto);

        insertDto = ReceiverDTO.builder()
                .valid(true)
                .receiverFiscalCode("fiscalCode1")
                .address("address1")
                .username("user1")
                .build();

        service.insertReceiver(insertDto);

        var receivers = service.findReceiversFromCodes(List.of("fiscalCode", "fiscalCode1"), "user1");
        assertEquals(2, receivers.size());
    }

    @Test
    public void whenInvokeFindReceiversFromCodesWithAUserThatHasntAnyReceiver_ShouldReturnAnEmptyList(){
        var receivers = service.findReceiversFromCodes(List.of("fiscalCode", "fiscalCode1"), "user2");
        assertEquals(0, receivers.size());
    }


}
