package com.spring.cqrs.command.api.events;

import com.spring.cqrs.command.api.data.Product;
import com.spring.cqrs.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("product")
public class ProductsEventHandler {

    private final ProductRepository productRepository;

    public ProductsEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void addProductEvent(ProductCreatedEvent event)  {

        Product product = new Product();

        BeanUtils.copyProperties(event , product);

        productRepository.save(product);

        //throw new Exception("Exception Occurred");
    }


    @EventHandler
    public void updateProductEvent(ProductUpdateEvent productUpdateEvent) throws Exception {
      //  System.out.println("ekhane asche");
       // System.out.println(productUpdateEvent.getProductId());

        Optional<Product> optionalProduct = productRepository.findById
                (
                        productUpdateEvent.getProductId()
                );

        if(optionalProduct.isPresent()){

            Product product = optionalProduct.get();

            BeanUtils.copyProperties(productUpdateEvent , product);

            productRepository.save(product);
        }else{
            throw new Exception("Product not found");
        }
    }

    @EventHandler
    public void deleteProductEvent(ProductDeleteEvent productDeleteEvent){
         productRepository.deleteById(productDeleteEvent.getProductId());
    }


    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }


}
