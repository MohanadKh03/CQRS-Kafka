package com.example.orderwrite;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderWriteRepository extends JpaRepository<OrderWritten, UUID> {
}
