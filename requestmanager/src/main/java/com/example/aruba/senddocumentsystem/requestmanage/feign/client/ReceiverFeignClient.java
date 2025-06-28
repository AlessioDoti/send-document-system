package com.example.aruba.senddocumentsystem.requestmanage.feign.client;

import com.example.aruba.senddocumentsystem.requestmanage.feign.dto.ReceiverDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "receivers", url = "${clients.receivers.url}")
public interface ReceiverFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/receivers/internal")
    List<ReceiverDTO> getReceiversFromCodes(@RequestParam("codes") List<String> codes, @RequestParam("user") String user);

}
