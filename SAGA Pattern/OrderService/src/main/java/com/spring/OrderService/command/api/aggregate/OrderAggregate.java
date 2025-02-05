package com.spring.OrderService.command.api.aggregate;


import com.spring.OrderService.command.api.command.CreateOrderCommand;
import com.spring.OrderService.command.api.events.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private String orderStatus;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        //Validate The Command
        OrderCreatedEvent orderCreatedEvent
                = new OrderCreatedEvent();
        BeanUtils.copyProperties(createOrderCommand,
                orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);
    }

}
