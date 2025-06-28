package com.example.aruba.senddocumentsystem.requestmanage.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "REQUEST_LOG_DOCUMENTS")
@Data
public class RequestLogDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DOCUMENT")
    private String document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUEST_LOG_ID", nullable = false)
    private RequestLog requestLog;
}

