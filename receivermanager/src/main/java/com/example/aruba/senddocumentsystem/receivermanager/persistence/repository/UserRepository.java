package com.example.aruba.senddocumentsystem.receivermanager.persistence.repository;

import com.example.aruba.senddocumentsystem.receivermanager.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
