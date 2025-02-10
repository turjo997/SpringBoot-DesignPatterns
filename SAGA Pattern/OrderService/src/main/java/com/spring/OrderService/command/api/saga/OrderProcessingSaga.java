package com.spring.OrderService.command.api.saga;

import com.spring.CommomService.commands.ValidatePaymentCommand;
import com.spring.OrderService.command.api.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    public OrderProcessingSaga() {
    }


    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id : {}",
                event.getOrderId());

//        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery
//                = new GetUserPaymentDetailsQuery(event.getUserId());

//        User user = null;
//
//        try {
//            user = queryGateway.query(
//                    getUserPaymentDetailsQuery,
//                    ResponseTypes.instanceOf(User.class)
//            ).join();
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            //Start the Compensating transaction
//            cancelOrderCommand(event.getOrderId());
//        }
//
//        ValidatePaymentCommand validatePaymentCommand
//                = ValidatePaymentCommand
//                .builder()
//                .cardDetails(user.getCardDetails())
//                .orderId(event.getOrderId())
//                .paymentId(UUID.randomUUID().toString())
//                .build();

      //  commandGateway.sendAndWait(validatePaymentCommand);
    }


}
