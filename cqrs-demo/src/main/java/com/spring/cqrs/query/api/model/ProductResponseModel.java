package com.spring.cqrs.query.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponseModel {

    private String name;
    private BigDecimal price;
    private Integer quantity;

}
