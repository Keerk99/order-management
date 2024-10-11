package com.keerk99.ordermanagement.domain.order;

import com.keerk99.ordermanagement.domain.order.dto.CreateOrder;
import com.keerk99.ordermanagement.domain.order.dto.DataListOrder;
import com.keerk99.ordermanagement.domain.order.dto.UpdateOrder;
import com.keerk99.ordermanagement.domain.product.dto.DataListProduct;

import java.util.List;

public class OrderMapper {
    public static Order toOrder(CreateOrder createOrder, List<DataListProduct> dataListProducts) {
        double total = dataListProducts
                .stream()
                .mapToDouble(DataListProduct::price)
                .sum();

        Order order = new Order();
        order.setCustomerId(createOrder.customerId());
        order.setProducts(createOrder.productList());
        order.setTotal(total);

        return order;
    }

    public static DataListOrder toDataListOrder(Order order, List<DataListProduct> dataListProducts) {
        return new DataListOrder(order, dataListProducts);
    }

    public static void toUpdateOrder(UpdateOrder updateOrder, Order order) {
        order.setProducts(updateOrder.productList());
    }
}
