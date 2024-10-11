package com.keerk99.ordermanagement.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("product")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock_quantity;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
