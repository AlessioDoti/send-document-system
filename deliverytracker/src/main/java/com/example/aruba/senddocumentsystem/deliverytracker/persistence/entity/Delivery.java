package com.example.aruba.senddocumentsystem.deliverytracker.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "DELIVERY")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "TRACEPARENT", unique = true)
    private String traceparent;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "INSERT_TS", nullable = false, updatable = false)
    private Timestamp insertTs;

    @Column(name = "UPDATE_TS")
    private Timestamp updateTs;

}
