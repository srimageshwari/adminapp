package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.ProductInfo;

import java.util.List;

public interface ProductDao {

    ProductInfo update(int id, ProductInfo productAttributes);

    ProductInfo get(int id);

    ProductInfo insert(ProductInfo productAttributes);

   void delete(int userId);

    List<ProductInfo> getAll();
}
