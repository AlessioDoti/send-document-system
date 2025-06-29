package com.example.aruba.senddocumentsystem.requestmanager.persistence.mapper;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.enumeration.DeliveryType;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLog;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLogDocument;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLogReceiver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RequestMapperTest {

    @InjectMocks
    private RequestLogMapper mapper;

    @Test
    public void whenInvokeFromDTOWithAValidDTO_ShouldReturnAMappedEntity(){
        var dto = RequestDTO.builder()
                .username("aaa")
                .receivers(List.of("receiver"))
                .addresses(List.of("address"))
                .documents(List.of("documents"))
                .deliveryType(DeliveryType.CERTIFIED)
                .traceParent("traceparent")
                .build();

        var entity = mapper.fromDTO(dto);

        assertNotNull(entity);
        assertEquals(dto.getUsername(), entity.getUsername());
        assertEquals(dto.getDeliveryType().toString(), entity.getDeliveryType());

    }

    @Test
    public void whenInvokeFromDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.fromDTO(null));
    }

    @Test
    public void whenInvokeToDTOWithAValidEntity_ShouldReturnAMappedDTO(){
        var entity = new RequestLog();
        entity.setUsername("user");
        entity.setDeliveryType("CERTIFIED");
        entity.setReceivers(List.of(new RequestLogReceiver()));
        entity.setDocuments(List.of(new RequestLogDocument()));
        var dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getDeliveryType(), dto.getDeliveryType().toString());

    }

    @Test
    public void whenInvokeToDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.toDTO(null));
    }
}
