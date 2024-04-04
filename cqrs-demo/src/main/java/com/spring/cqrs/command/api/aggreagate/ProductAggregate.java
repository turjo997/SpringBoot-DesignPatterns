package com.spring.cqrs.command.api.aggreagate;

import com.spring.cqrs.command.api.commands.CreateProductCommand;
import com.spring.cqrs.command.api.commands.UpdateProductCommand;
import com.spring.cqrs.command.api.commands.DeleteProductCommand;
import com.spring.cqrs.command.api.events.ProductCreatedEvent;
import com.spring.cqrs.command.api.events.ProductDeleteEvent;
import com.spring.cqrs.command.api.events.ProductUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

        BeanUtils.copyProperties(createProductCommand , productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);

    }


    public ProductAggregate(){

    }


    @CommandHandler
    public void updateProductAggregate(UpdateProductCommand updateProductCommand){
        ProductUpdateEvent productUpdateEvent = new ProductUpdateEvent();

        BeanUtils.copyProperties(updateProductCommand , productUpdateEvent);

        AggregateLifecycle.apply(productUpdateEvent);
    }


    @CommandHandler
    public void deleteProductAggregate(DeleteProductCommand deleteProductCommand){

        AggregateLifecycle.apply(new ProductDeleteEvent(deleteProductCommand.getProductId()));

    }


    @EventSourcingHandler
    public void updateStateByAdding(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }


    @EventSourcingHandler
    public void updateStateByModifying(ProductUpdateEvent productUpdateEvent){
        this.productId = productUpdateEvent.getProductId();
        this.name = productUpdateEvent.getName();
        this.quantity = productUpdateEvent.getQuantity();
        this.price = productUpdateEvent.getPrice();
    }


    @EventSourcingHandler
    public void updateStateByDeleting(ProductDeleteEvent productDeleteEvent){
        this.productId = productDeleteEvent.getProductId();
    }

}
