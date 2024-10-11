package com.keerk99.ordermanagement.domain.order.impl;

import com.keerk99.ordermanagement.domain.customer.CustomerService;
import com.keerk99.ordermanagement.domain.order.Order;
import com.keerk99.ordermanagement.domain.order.OrderMapper;
import com.keerk99.ordermanagement.domain.order.OrderRepository;
import com.keerk99.ordermanagement.domain.order.OrderService;
import com.keerk99.ordermanagement.domain.order.dto.CreateOrder;
import com.keerk99.ordermanagement.domain.order.dto.DataListOrder;
import com.keerk99.ordermanagement.domain.order.dto.UpdateOrder;
import com.keerk99.ordermanagement.domain.product.ProductService;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public Mono<Void> createOrder(CreateOrder createOrder) {
        Mono<Boolean> customerExists = customerService.existsCustomer(createOrder.customerId());

        Flux<DataListProduct> validProductsFlux = Flux.fromIterable(createOrder.productList())
                .flatMap(productId -> productService.findProductById(productId)
                        .filter(Objects::nonNull));

        return customerExists.flatMap(customer -> {
            if (!customer) {
                return Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with ID " + createOrder.customerId() + " not found")
                ).then();
            }
            return validProductsFlux.collectList().flatMap(validProducts -> {
                if (validProducts.isEmpty()) {
                    return Mono.error(
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more products not found")
                    ).then();
                }
                Order order = OrderMapper.toOrder(createOrder, validProducts);
                return orderRepository.save(order).then();
            });
        });
    }

    @Override
    public Flux<DataListOrder> getAllOrders() {
        return orderRepository.findAll()
                .flatMap(order -> Flux.fromIterable(order.getProducts())
                        .flatMap(productService::findProductById)
                        .collectList()
                        .map(productList -> OrderMapper.toDataListOrder(order, productList)));
    }

    @Override
    public Mono<DataListOrder> findOrderById(String orderId) {
        if (!ObjectId.isValid(orderId)) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order ID"));
        }

        return orderRepository.findById(new ObjectId(orderId))
                .flatMap(order -> Flux.fromIterable(order.getProducts())
                        .flatMap(productService::findProductById)
                        .collectList()
                        .map(productList -> OrderMapper.toDataListOrder(order, productList)))
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with ID " + orderId + " not found")
                ));
    }

    @Transactional
    @Override
    public Mono<Void> updateOrder(String orderId, UpdateOrder updateOrder) {
        if (!ObjectId.isValid(orderId)) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order ID"));
        }

        return orderRepository.findById(new ObjectId(orderId))
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with ID " + orderId + " not found")
                ))
                .flatMap(order -> {
                    OrderMapper.toUpdateOrder(updateOrder, order);
                    return calculateTotal(order.getProducts())
                            .doOnNext(order::setTotal)
                            .flatMap(total -> orderRepository.save(order).then());
                });
    }

    @Transactional
    @Override
    public Mono<Void> deleteOrder(String orderId) {
        if (!ObjectId.isValid(orderId)) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order ID"));
        }

        return orderRepository.findById(new ObjectId(orderId))
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with ID " + orderId + " not found")
                ))
                .flatMap(orderRepository::delete);
    }

    private Mono<Double> calculateTotal(List<Long> productsIds) {
        return Flux.fromIterable(productsIds)
                .flatMap(productService::findProductById)
                .map(DataListProduct::price)
                .defaultIfEmpty(0.0)
                .reduce(0.0, Double::sum);
    }
}
