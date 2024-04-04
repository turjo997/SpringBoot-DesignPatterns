package com.spring.cqrs.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class DeleteProductCommand {

    @TargetAggregateIdentifier
    private String productId;

}
