package com.example.aruba.senddocumentsystem.internalnotificator.persistence.mapper;

import com.example.aruba.senddocumentsystem.internalnotificator.domain.dto.NotificationDTO;
import com.example.aruba.senddocumentsystem.internalnotificator.domain.enumeration.Status;
import com.example.aruba.senddocumentsystem.internalnotificator.persistence.entity.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NotificationMapperTest {

    @InjectMocks
    private NotificationMapper mapper;

    private static final String STATUS = "COMPLETED";
    private static final String TRACEPARENT = "traceParent";

    @Test
    public void whenInvokeFromDTOWithAValidDTO_ShouldReturnAMappedEntity(){
        var dto = NotificationDTO.builder()
                .status(Status.valueOf(STATUS))
                .traceParent(TRACEPARENT)
                .build();

        var entity = mapper.fromDTO(dto);

        assertNotNull(entity);
        assertEquals(dto.getStatus().toString(), entity.getStatus());
        assertEquals(dto.getTraceParent(), entity.getTraceParent());

    }

    @Test
    public void whenInvokeFromDTOWithAnInValidDTO_ShouldThrowNullPointerException(){
        var dto = NotificationDTO.builder()
                .build();

        assertThrows(NullPointerException.class, () -> mapper.fromDTO(dto));

    }

    @Test
    public void whenInvokeFromDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.fromDTO(null));
    }

    @Test
    public void whenInvokeToDTOWithAValidEntity_ShouldReturnAMappedDTO(){
        var entity = new Notification();
        entity.setStatus(STATUS);
        entity.setTraceParent(TRACEPARENT);

        var dto = mapper.toDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getStatus(), dto.getStatus().toString());
        assertEquals(entity.getTraceParent(), dto.getTraceParent());

    }


    @Test
    public void whenInvokeToDTOWithAnInvalidEntity_ShouldThrowNullPointerException(){
        var entity = new Notification();

        assertThrows(NullPointerException.class, () -> mapper.toDTO(entity));

    }

    @Test
    public void whenInvokeToDTOWithNull_ShouldThrowNullPointerException(){
        assertThrows(NullPointerException.class, () -> mapper.toDTO(null));
    }
}
