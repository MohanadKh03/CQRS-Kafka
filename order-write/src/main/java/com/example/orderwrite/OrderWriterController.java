package com.example.orderwrite;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.Order;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderWriterController {
    OrderWriteRepository writer;
    KafkaTemplate<String,Order>kafkaTemplate;

    @PostMapping
    ResponseEntity<Object> addOrder(@RequestBody Order order) {
        OrderWritten orderWritten = new OrderWritten();

        BeanUtils.copyProperties(order,orderWritten);

        OrderWritten o = writer.save(orderWritten);

        BeanUtils.copyProperties(o,order);

        kafkaTemplate.send("cqrs",order);

        return new ResponseEntity<>(o,HttpStatus.OK);

    }

}
