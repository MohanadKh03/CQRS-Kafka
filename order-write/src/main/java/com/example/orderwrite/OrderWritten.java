package com.example.orderwrite;

import com.example.common.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class OrderWritten {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    Double price;
    Payment paymentType;
}
