package com.b2s.service.adminapp.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class ProductInfo implements Comparable {
    private String name;
    private int points;
    private int productId;
    private ProductType type;
    private Optional<String> description;

    private ProductInfo(ProductAttributesBuilder builder) {
        this.name = builder.name;
        this.points = builder.points;
        this.productId = builder.productId;
        this.type = builder.type;
        this.description = builder.description;
    }

    @JsonCreator
    public static ProductInfo create(
            @JsonProperty("name") final String theName,
            @JsonProperty("points") final int thePoints,
            @JsonProperty("productId") final int theProductId,
            @JsonProperty("type") final ProductType theType,
            @JsonProperty("description") final Optional<String> theDescription
    ) {
        return builder().setName(theName).setPoints(thePoints).setProductId(theProductId).setType(theType).
                setDescription(theDescription).build();
    }

    public static ProductAttributesBuilder builder() {
        return new ProductAttributesBuilder();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getProductId() {
        return productId;
    }

    public ProductType getType() {
        return type;
    }

    public Optional<String> getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", productId=" + productId +
                ", type=" + type +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode())
            return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return productId;
    }

    @Override
    public int compareTo(Object o) {
        return this.hashCode() - o.hashCode();
    }

    public static class ProductAttributesBuilder {
        private String name;
        private int points;
        private int productId;
        private ProductType type;
        private Optional<String> description;


        public ProductAttributesBuilder() {
        }

        public ProductAttributesBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public ProductAttributesBuilder setPoints(final int points) {
            this.points = points;
            return this;
        }

        public ProductAttributesBuilder setProductId(final int productId) {
            this.productId = productId;
            return this;
        }

        public ProductAttributesBuilder setType(final ProductType type) {
            this.type = type;
            return this;
        }

        public ProductAttributesBuilder setDescription(Optional<String> description) {
            this.description = description;
            return this;
        }

        public ProductInfo build() {
            return new ProductInfo(this);
        }
    }

}
