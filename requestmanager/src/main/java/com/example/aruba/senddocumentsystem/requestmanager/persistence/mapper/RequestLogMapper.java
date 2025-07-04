package com.example.aruba.senddocumentsystem.requestmanager.persistence.mapper;

import com.example.aruba.senddocumentsystem.requestmanager.domain.dto.RequestDTO;
import com.example.aruba.senddocumentsystem.requestmanager.domain.enumeration.DeliveryType;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLog;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLogDocument;
import com.example.aruba.senddocumentsystem.requestmanager.persistence.entity.RequestLogReceiver;
import org.springframework.stereotype.Component;

@Component
public class RequestLogMapper {

    public RequestLog fromDTO(RequestDTO dto){
        var requestLog = new RequestLog();
        requestLog.setDeliveryType(dto.getDeliveryType().toString());
        requestLog.setUsername(dto.getUsername());
        requestLog.setTraceParent(dto.getTraceParent());
        requestLog.setReceivers(dto.getAddresses()
                .stream()
                .map(a -> buildReqLogReceiver(a, requestLog))
                .toList());
        requestLog.setDocuments(dto.getDocuments()
                .stream()
                .map(d -> buildReqLogDocument(d, requestLog))
                .toList());
        return requestLog;
    }

    public RequestDTO toDTO(RequestLog requestLog){
        return RequestDTO.builder()
                .traceParent(requestLog.getTraceParent())
                .username(requestLog.getUsername())
                .deliveryType(DeliveryType.valueOf(requestLog.getDeliveryType().toUpperCase()))
                .addresses(requestLog.getReceivers().stream().map(RequestLogReceiver::getReceiver).toList())
                .documents(requestLog.getDocuments().stream().map(RequestLogDocument::getDocument).toList())
                .build();
    }

    private RequestLogReceiver buildReqLogReceiver(String address, RequestLog requestLog){
        var reqLogReceiver = new RequestLogReceiver();
        reqLogReceiver.setRequestLog(requestLog);
        reqLogReceiver.setReceiver(address);
        return reqLogReceiver;
    }

    private RequestLogDocument buildReqLogDocument(String document, RequestLog requestLog){
        var reqLogReceiver = new RequestLogDocument();
        reqLogReceiver.setRequestLog(requestLog);
        reqLogReceiver.setDocument(document);
        return reqLogReceiver;
    }
}
