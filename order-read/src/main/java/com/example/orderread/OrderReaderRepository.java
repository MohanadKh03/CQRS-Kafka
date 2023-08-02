package com.example.orderread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderReaderRepository extends JpaRepository<OrderRead, UUID> {
}
