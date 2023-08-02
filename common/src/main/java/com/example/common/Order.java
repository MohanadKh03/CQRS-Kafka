package com.example.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Order {
    UUID id;
    Double price;
    Payment paymentType;
}
