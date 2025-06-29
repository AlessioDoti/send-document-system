package com.example.aruba.senddocumentsystem.requestmanager.domain.port.feign;

import java.util.List;

public interface ReceiverRestService {
    List<String> getAddresses(List<String> codes, String username);
}
