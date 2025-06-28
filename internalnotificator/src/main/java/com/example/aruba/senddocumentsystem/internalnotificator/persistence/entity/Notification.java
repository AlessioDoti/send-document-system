package com.example.aruba.senddocumentsystem.internalnotificator.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "NOTIFICATIONS",
        uniqueConstraints = @UniqueConstraint(name = "UNIQUE_STATUS_TRACEPARENT", columnNames = {"STATUS", "TRACEPARENT"}))
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "TRACEPARENT", nullable = false)
    private String traceParent;
}
