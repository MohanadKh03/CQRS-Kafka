package com.example.orderread;

import com.example.common.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("order")
@AllArgsConstructor
public class OrderReaderController {

    OrderReaderRepository reader;

    @GetMapping
    ResponseEntity<Object> getAllOrders(){
        return new ResponseEntity<>(reader.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Object> getOrder(@PathVariable UUID id){
        return new ResponseEntity<>(reader.findById(id), HttpStatus.OK);
    }

    @KafkaListener(topics = "cqrs",groupId = "group_id")
    public void listen(Order order){
        System.out.println("Order from kafka: " + order);
        OrderRead readValue = new OrderRead();
        BeanUtils.copyProperties(order,readValue);
        reader.save(readValue);
        System.out.println("Read value: " + readValue);
    }

}
