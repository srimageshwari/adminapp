package com.b2s.service.adminapp.model;

public class Product {
    private String name;
    private int points;
    private int productId;
    private ProductType type;

    public Product(String name, int points, int productId, ProductType type) {
        this.name = name;
        this.points = points;
        this.productId = productId;
        this.type = type;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (points != product.points) return false;
        if (productId != product.productId) return false;
        if (!name.equals(product.name)) return false;
        return type.equals(product.type);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + points;
        result = 31 * result + productId;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
public String toString() {
    return "Product{" +
            "name='" + name + '\'' +
            ", points=" + points +
            ", productId=" + productId +
            ", type='" + type + '\'' +
            '}';
}}
