package com.b2s.service.adminapp.service;


import com.b2s.service.adminapp.exception.InvalidRequestException;
import com.b2s.service.adminapp.model.ProductInfo;
import org.springframework.stereotype.Component;

/**
 * @author smuthuvel 2018-12-11
 */
@Component
public class ProductValidator {
    public void validateAttributes(ProductInfo productAttributes) {
        if (productAttributes.getPoints() == 0) {
            throw new InvalidRequestException("enter the points");
        }
       if (productAttributes.getName()== null) {
            throw new InvalidRequestException("Enter the name");
        }
    }
}
