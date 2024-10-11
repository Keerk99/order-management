package com.keerk99.ordermanagement.controller;

import com.keerk99.ordermanagement.domain.order.OrderService;
import com.keerk99.ordermanagement.domain.order.dto.CreateOrder;
import com.keerk99.ordermanagement.domain.order.dto.DataListOrder;
import com.keerk99.ordermanagement.domain.order.dto.UpdateOrder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<String>> createOrder(@Valid @RequestBody CreateOrder createOrder) {
        return orderService.createOrder(createOrder)
                .then(Mono.just(
                        new ResponseEntity<>("Order created successfully", HttpStatus.CREATED)
                ));
    }

    @GetMapping
    public Mono<ResponseEntity<List<DataListOrder>>> getAllOrders() {
        return orderService.getAllOrders()
                .collectList()
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK));
    }

    @GetMapping("/{orderId}")
    public Mono<ResponseEntity<DataListOrder>> getOrderById(@PathVariable String orderId) {
        return orderService.findOrderById(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{orderId}")
    public Mono<ResponseEntity<String>> updateOrder(@PathVariable String orderId, @Valid @RequestBody UpdateOrder updateOrder) {
        return orderService.updateOrder(orderId, updateOrder)
                .then(Mono.just(
                        new ResponseEntity<>("Order updated successfully", HttpStatus.OK)
                ));
    }

    @DeleteMapping("/{orderId}")
    public Mono<ResponseEntity<String>> deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId)
                .then(Mono.just(
                        new ResponseEntity<>("Order deleted successfully", HttpStatus.OK)
                ));
    }
}
