package com.example.aruba.senddocumentsystem.internalnotificator.persistence.repository;

import com.example.aruba.senddocumentsystem.internalnotificator.persistence.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
