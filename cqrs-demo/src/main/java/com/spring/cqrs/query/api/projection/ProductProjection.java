package com.spring.cqrs.query.api.projection;

import com.spring.cqrs.command.api.data.Product;
import com.spring.cqrs.command.api.repository.ProductRepository;
import com.spring.cqrs.query.api.model.ProductResponseModel;
import com.spring.cqrs.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @QueryHandler
    public List<ProductResponseModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products =
                productRepository.findAll();

        List<ProductResponseModel> productResponseModels =

                products.stream()
                        .map(product -> ProductResponseModel
                                .builder()
                                .quantity(product.getQuantity())
                                .price(product.getPrice())
                                .name(product.getName())
                                .build())
                        .collect(Collectors.toList());

        return productResponseModels;
    }

}
