package com.example.aruba.senddocumentsystem.requestmanage.persistence.repository;

import com.example.aruba.senddocumentsystem.requestmanage.persistence.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
}
