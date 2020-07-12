package com.myretail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Product implements Serializable {

    private String id;
    private String name;

    @JsonProperty("current_price")
    private Price price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
