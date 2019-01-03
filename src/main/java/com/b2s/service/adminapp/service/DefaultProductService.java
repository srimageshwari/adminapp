package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.dao.MapProductDao;
import com.b2s.service.adminapp.dao.ProductDao;
import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultProductService implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(DefaultProductService.class);
    private ProductDao productDao;
    private ProductValidator productValidator;


    public DefaultProductService(ProductValidator productValidator, MapProductDao mapProductDao) {
        this.productValidator = productValidator;
        this.productDao = mapProductDao;
    }


    public ProductInfo insert(ProductInfo productAttributes) {
        productValidator.validateAttributes(productAttributes);
        return productDao.insert(productAttributes);
    }

    public ProductInfo get(int id) {
        return productDao.get(id);
    }

    public ProductInfo update(int id, ProductInfo productAttributes) {
        LOGGER.debug("product update with productId:{}", productAttributes.getProductId());
        productValidator.validateAttributes(productAttributes);
        productDao.update(id, productAttributes);
        return ProductInfo.builder().setName(productAttributes.getName()).setPoints(productAttributes.getPoints())
                .setProductId(id).
                        setType(productAttributes.getType()).build();
    }

    public void delete(int id) {
        productDao.delete(id);

    }

    public List<ProductInfo> getAll() {
        return productDao.getAll();
    }

    public List<Product> getByType(int points, String type) {
        List<ProductInfo> productDetail = productDao.getAll();
        List<Product> productList = productDetail.stream().filter(productAttributes -> productAttributes.getType().name().
                equals(type) && productAttributes.getPoints() == points).map(products ->
        {
            Product product = new Product();
            product.setName(products.getName());
            product.setPoints(products.getPoints());
            product.setProductId(products.getProductId());
            product.setType(products.getType());
            return product;
        }).collect(Collectors.toList());
        System.out.println(productList);
        return productList;
    }
}















    /*
       public  ProductInfo createproducttoservice(ProductInfo productAttributes)
        {
            return productdao.createproduct(productAttributes);
        }
      public  ProductInfo callbyidtoDao(int userid)
      {
          return productdao.callbyid(userid);
        }
    */