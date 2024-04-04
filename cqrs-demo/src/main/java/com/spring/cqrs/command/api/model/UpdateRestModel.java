package com.spring.cqrs.command.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UpdateRestModel {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

}
