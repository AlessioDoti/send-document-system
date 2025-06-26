package com.example.aruba.senddocumentsystem.receivermanager.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@Table(name = "RECEIVER")
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FISCAL_CODE", nullable = false, unique = true)
    private String fiscalCode;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "IS_VALID")
    private boolean valid;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "RECEIVERS_USERS",
            joinColumns = @JoinColumn(name = "RECEIVER_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID")
    )
    private Set<User> users;
}
