package com.b2s.service.adminapp.controller;


import com.b2s.service.adminapp.model.Product;
import com.b2s.service.adminapp.model.ProductInfo;
import com.b2s.service.adminapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    private static final String Save_URL = "/products";
    private final ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = Save_URL)
    public ProductInfo create(@RequestBody final ProductInfo productAttributes) {
        LOGGER.info("{}{}:createProduct={}", RequestMethod.POST, Save_URL, productAttributes);
        return productService.insert(productAttributes);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products/{id}")
    public ProductInfo get(@PathVariable int id) {
        return productService.get(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products")
    public List<ProductInfo> getAll() {
        return productService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/products/{id}")
    public ResponseEntity update(@PathVariable final int id,
                                 @RequestBody final ProductInfo productAttributes) {
        ProductInfo updatedProduct = productService.update(id, productAttributes);
        return ResponseEntity.ok(updatedProduct);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/products/{points}/{type}")
    public List<Product> getByType(@PathVariable int points, @PathVariable String type) {
        return productService.getByType(points, type);
    }
}



















































  /*
    @PostMapping(value="/product/createproduct")

    public ProductInfo createproduct(@RequestBody ProductInfo productAttributes)
    {
        return productService.createproducttoservice(productAttributes);
    }


   @GetMapping(value="/product/{userid}")
    public ProductInfo callbyidtoservice(@PathVariable ("userid")  int id)
    {
        return productService.callbyidtoDao(id);

 }*/