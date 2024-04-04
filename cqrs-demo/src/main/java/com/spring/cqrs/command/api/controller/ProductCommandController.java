package com.spring.cqrs.command.api.controller;

import com.spring.cqrs.command.api.commands.CreateProductCommand;
import com.spring.cqrs.command.api.commands.UpdateProductCommand;
import com.spring.cqrs.command.api.commands.DeleteProductCommand;
import com.spring.cqrs.command.api.model.ProductRestModel;
import com.spring.cqrs.command.api.model.UpdateRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

   private CommandGateway commandGateway;


    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel){

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();

        String result = commandGateway.sendAndWait(createProductCommand);

        return result;
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody UpdateRestModel productRestModel){

        UpdateProductCommand updateProductCommand = UpdateProductCommand.builder()
                .productId(productRestModel.getId())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();

        String result = commandGateway.sendAndWait(updateProductCommand);

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id){

        DeleteProductCommand deleteProductCommand = new DeleteProductCommand(id);
        return commandGateway.sendAndWait(deleteProductCommand);
    }

}
