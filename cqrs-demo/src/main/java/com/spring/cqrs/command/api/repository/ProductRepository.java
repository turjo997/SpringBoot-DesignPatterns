package com.spring.cqrs.command.api.repository;

import com.spring.cqrs.command.api.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product , String> {
}
