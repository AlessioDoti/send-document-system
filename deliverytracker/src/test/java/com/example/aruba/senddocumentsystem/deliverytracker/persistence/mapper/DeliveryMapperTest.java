package com.example.aruba.senddocumentsystem.deliverytracker.persistence.mapper;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.entity.Delivery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryMapperTest {

    @InjectMocks
    private DeliveryMapper mapper;

    private static final String USERNAME = "user";
    private static final String STATUS = "COMPLETED";
    private static final String TRACEPARENT = "traceParent";
    private static final Timestamp TIMESTAMP = Timestamp.from(Instant.now());

    @Test
    public void whenInvokeFromDTOWithAValidDTO_ShouldReturnAMappedEntity(){
        var dto = DeliveryDTO.builder()
                .username(USERNAME)
                .status(Status.valueOf(STATUS))
                .traceParent(TRACEPARENT)
                .timestamp(TIMESTAMP)
                .build();

        var entity = mapper.fromDTO(dto);

        assertNotNull(entity);
        assertEquals(dto.getStatus().toString(), entity.getStatus());
        assertEquals(dto.getTraceParent(), entity.getTraceparent());
        assertEquals(dto.getTimestamp(), entity.getInsertTs());
        assertEquals(dto.getUsername(), entity.getUsername());

    }

    @Test
    public void whenInvokeFromDTOWithAnInValidDTO_ShouldThrowNullPointerException(){
        var dto = DeliveryDTO.builder()
                .build();

        assertThrows(NullPointerException.class, () -> mapper.fromDTO(dto));

    }

    @Test
    public void whenInvokeFromDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.fromDTO(null));
    }

    @Test
    public void whenInvokeToDTOWithAValidEntity_ShouldReturnAMappedDTO(){
        var entity = new Delivery();
        entity.setInsertTs(TIMESTAMP);
        entity.setUsername(USERNAME);
        entity.setStatus(STATUS);
        entity.setTraceparent(TRACEPARENT);

        var dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getStatus(), dto.getStatus().toString());
        assertEquals(entity.getTraceparent(), dto.getTraceParent());
        assertEquals(entity.getInsertTs(), dto.getTimestamp());
        assertEquals(entity.getUsername(), dto.getUsername());

    }

    @Test
    public void whenInvokeToDTOWithAValidEntityAndUpdateTS_ShouldReturnAMappedDTOWithUpdateTs(){
        var updTs = Timestamp.from(Instant.now().plusSeconds(30));
        var entity = new Delivery();
        entity.setInsertTs(TIMESTAMP);
        entity.setUsername(USERNAME);
        entity.setStatus(STATUS);
        entity.setTraceparent(TRACEPARENT);
        entity.setUpdateTs(updTs);

        var dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getStatus(), dto.getStatus().toString());
        assertEquals(entity.getTraceparent(), dto.getTraceParent());
        assertEquals(entity.getUsername(), dto.getUsername());
        assertEquals(entity.getUpdateTs(), dto.getTimestamp());

    }

    @Test
    public void whenInvokeToDTOWithAnInvalidEntity_ShouldThrowNullPointerException(){
        var entity = new Delivery();

        assertThrows(NullPointerException.class, () -> mapper.toDTO(entity));

    }

    @Test
    public void whenInvokeToDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.toDTO(null));
    }
}
