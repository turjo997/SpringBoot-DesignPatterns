package com.spring.cqrs.command.api.events;

import com.spring.cqrs.command.api.data.Product;
import com.spring.cqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductsEventHandler {

    private final ProductRepository productRepository;

    public ProductsEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {

        Product product = new Product();

        BeanUtils.copyProperties(event , product);

        productRepository.save(product);

        throw new Exception("Exception Occurred");
    }


    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }


}
