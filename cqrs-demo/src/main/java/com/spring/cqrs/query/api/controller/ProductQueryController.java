package com.spring.cqrs.query.api.controller;

import com.spring.cqrs.query.api.model.ProductResponseModel;
import com.spring.cqrs.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;


    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }


    @GetMapping
    public List<ProductResponseModel> getAllProducts(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();


        List<ProductResponseModel> productResponseModels =  queryGateway.
                query(getProductsQuery ,
                ResponseTypes.multipleInstancesOf(ProductResponseModel.class))
                .join();

        return productResponseModels;
    }





}
