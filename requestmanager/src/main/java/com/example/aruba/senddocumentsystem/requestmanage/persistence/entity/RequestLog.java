package com.example.aruba.senddocumentsystem.requestmanage.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "REQUEST_LOG")
@Data
public class RequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TRACEPARENT")
    private String traceParent;

    @Column(name = "DELIVERY_TYPE")
    private String deliveryType;

    @OneToMany(mappedBy = "requestLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestLogDocument> documents;

    @OneToMany(mappedBy = "requestLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestLogReceiver> receivers;
}
