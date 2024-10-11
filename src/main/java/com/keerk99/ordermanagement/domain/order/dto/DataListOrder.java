package com.keerk99.ordermanagement.domain.order.dto;

import com.keerk99.ordermanagement.domain.order.Order;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;

import java.util.List;

public record DataListOrder(
        String id,
        Long customerId,
        List<DataListProduct> productList,
        Double total
) {
    public DataListOrder(Order order, List<DataListProduct> productList) {
        this(order.getId().toString(), order.getCustomerId(),
                productList, order.getTotal());
    }
}
