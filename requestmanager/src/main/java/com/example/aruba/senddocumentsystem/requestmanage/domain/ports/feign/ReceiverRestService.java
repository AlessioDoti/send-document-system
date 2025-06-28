package com.example.aruba.senddocumentsystem.requestmanage.domain.ports.feign;

import java.util.List;

public interface ReceiverRestService {
    List<String> getAddresses(List<String> codes, String username);
}
