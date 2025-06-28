package com.example.aruba.senddocumentsystem.requestmanage.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "REQUEST_LOG_RECEIVERS")
@Data
public class RequestLogReceiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RECEIVER")
    private String receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUEST_LOG_ID", nullable = false)
    private RequestLog requestLog;
}
