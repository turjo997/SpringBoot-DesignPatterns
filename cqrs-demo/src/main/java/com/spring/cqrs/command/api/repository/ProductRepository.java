package com.spring.cqrs.command.api.repository;

import com.spring.cqrs.command.api.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product , String> {
    List<Product> findByName(String name);

}
