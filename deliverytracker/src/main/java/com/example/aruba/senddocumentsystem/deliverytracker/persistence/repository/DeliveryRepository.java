package com.example.aruba.senddocumentsystem.deliverytracker.persistence.repository;

import com.example.aruba.senddocumentsystem.deliverytracker.persistence.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Optional<Delivery> findByTraceparentAndUsername(String traceparent, String username);
}
