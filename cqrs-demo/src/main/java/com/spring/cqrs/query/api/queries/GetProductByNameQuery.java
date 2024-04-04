package com.spring.cqrs.query.api.queries;

public class GetProductByNameQuery {
    private String name;

    public GetProductByNameQuery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
