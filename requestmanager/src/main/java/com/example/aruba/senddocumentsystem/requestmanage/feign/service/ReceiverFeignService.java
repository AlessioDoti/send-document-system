package com.example.aruba.senddocumentsystem.requestmanage.feign.service;

import com.example.aruba.senddocumentsystem.requestmanage.domain.port.feign.ReceiverRestService;
import com.example.aruba.senddocumentsystem.requestmanage.feign.client.ReceiverFeignClient;
import com.example.aruba.senddocumentsystem.requestmanage.feign.dto.ReceiverDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiverFeignService implements ReceiverRestService {

    @Autowired
    private final ReceiverFeignClient client;

    @Override
    @CircuitBreaker(name = "receivers", fallbackMethod = "addressesFallback")
    public List<String> getAddresses(List<String> codes, String user){
        return client.getReceiversFromCodes(codes, user)
                .stream()
                .map(ReceiverDTO::getAddress)
                .toList();
    }

    public List<String> addressesFallback(List<String> codes, String user, Throwable t){
        throw new RuntimeException("Unable to get receiver addresses", t);
    }
}
