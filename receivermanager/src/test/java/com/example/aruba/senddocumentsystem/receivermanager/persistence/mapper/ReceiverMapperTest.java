package com.example.aruba.senddocumentsystem.receivermanager.persistence.mapper;

import com.example.aruba.senddocumentsystem.receivermanager.domain.dto.ReceiverDTO;
import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.Receiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReceiverMapperTest {

    @InjectMocks
    private ReceiverMapper mapper;

    private static final String STATUS = "COMPLETED";
    private static final String TRACEPARENT = "traceParent";

    @Test
    public void whenInvokeFromDTOWithAValidDTO_ShouldReturnAMappedEntity(){
        var dto = ReceiverDTO.builder()
                .valid(true)
                .address("aaa@aaa.it")
                .username("aaa")
                .receiverFiscalCode("fiscalcode")
                .build();

        var entity = mapper.fromDTO(dto);

        assertNotNull(entity);
        assertEquals(dto.isValid(), entity.isValid());
        assertEquals(dto.getAddress(), entity.getAddress());
        assertEquals(dto.getReceiverFiscalCode(), entity.getFiscalCode());
        assertEquals(new HashSet<>(), entity.getUsers());

    }

    @Test
    public void whenInvokeFromDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.fromDTO(null));
    }

    @Test
    public void whenInvokeToDTOWithAValidEntity_ShouldReturnAMappedDTO(){
        var entity = new Receiver();
        entity.setValid(true);
        entity.setAddress("address");
        entity.setFiscalCode("fiscalCode");

        var dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.isValid(), dto.isValid());
        assertEquals(entity.getAddress(), dto.getAddress());
        assertEquals(entity.getFiscalCode(), dto.getReceiverFiscalCode());

    }

    @Test
    public void whenInvokeToDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.toDTO(null));
    }
}
