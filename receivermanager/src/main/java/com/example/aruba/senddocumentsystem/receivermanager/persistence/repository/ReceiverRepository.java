package com.example.aruba.senddocumentsystem.receivermanager.persistence.repository;

import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.Receiver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Long> {

    Page<Receiver> findByUsers_Username(String username, Pageable pageable);

    Optional<Receiver> findByFiscalCode(String fiscalCode);

    List<Receiver> findByFiscalCodeInAndUsers_UsernameAndValidTrue(List<String> fiscalCodes, String username);

}
