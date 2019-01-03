package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;

import java.util.List;

public interface ProductService {

    ProductInfo get(int id);
    ProductInfo update(int id, ProductInfo productAttributes);
    ProductInfo insert(ProductInfo productAttributes);
    void delete(int id);
    List<ProductInfo> getAll();
    List<Product> getByType(int points, String type);


}

