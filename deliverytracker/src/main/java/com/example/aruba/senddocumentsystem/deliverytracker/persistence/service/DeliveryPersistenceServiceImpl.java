package com.example.aruba.senddocumentsystem.deliverytracker.persistence.service;

import com.example.aruba.senddocumentsystem.deliverytracker.domain.dto.DeliveryDTO;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.exception.DeliveryNotFoundException;
import com.example.aruba.senddocumentsystem.deliverytracker.domain.port.persistence.DeliveryPersistenceService;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.entity.Delivery;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.mapper.DeliveryMapper;
import com.example.aruba.senddocumentsystem.deliverytracker.persistence.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryPersistenceServiceImpl implements DeliveryPersistenceService {

    @Autowired
    private final DeliveryRepository repository;

    @Autowired
    private final DeliveryMapper mapper;

    @Override
    public DeliveryDTO persistDelivery(DeliveryDTO dto) {
        var delivery = repository.findByTraceparentAndUsername(dto.getRequestTraceParent(), dto.getUsername()).orElse(null);
        if(Objects.isNull(delivery)){
            log.info("Delivery not existing, it will be created");
            delivery = mapper.fromDTO(dto);
        } else{
            log.info("Delivery already exists with id {} it will be updated", delivery.getId().toString());
            updateStaus(delivery, dto);
        }

        return mapper.toDTO(repository.save(delivery));
    }

    @Override
    public DeliveryDTO findByUsernameAndTraceParent(String username, String traceParent) {
        var delivery = repository.findByTraceparentAndUsername(username, traceParent).orElse(null);
        if(Objects.nonNull(delivery)){
            log.info("Delivery {} Found", traceParent);
            return mapper.toDTO(delivery);
        } else{
            throw new DeliveryNotFoundException("Delivery " + traceParent + " not Found");
        }
    }

    private void updateStaus(Delivery delivery, DeliveryDTO dto){
        delivery.setUpdateTs(dto.getTimestamp());
        delivery.setStatus(dto.getStatus().toString());
    }
}
