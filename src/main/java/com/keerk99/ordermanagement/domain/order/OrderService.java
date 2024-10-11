package com.keerk99.ordermanagement.domain.order;

import com.keerk99.ordermanagement.domain.order.dto.CreateOrder;
import com.keerk99.ordermanagement.domain.order.dto.DataListOrder;
import com.keerk99.ordermanagement.domain.order.dto.UpdateOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Void> createOrder(CreateOrder createOrder);
    Flux<DataListOrder> getAllOrders();
    Mono<DataListOrder> findOrderById(String orderId);
    Mono<Void> updateOrder(String orderId, UpdateOrder updateOrder);
    Mono<Void> deleteOrder(String orderId);
}
