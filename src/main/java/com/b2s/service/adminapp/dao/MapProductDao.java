package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.ProductInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smuthuvel 2018-12-20
 */
@Component
public class MapProductDao implements ProductDao {

    Map<Integer, ProductInfo> mapProductDao = new HashMap<>();


    public Map<Integer, ProductInfo> getMapProductDao() {
        return mapProductDao;
    }


    public ProductInfo insert(ProductInfo productAttributes) {
        mapProductDao.put(productAttributes.getProductId(), productAttributes);
        return get(productAttributes.getProductId());
    }

    public ProductInfo update(int id, ProductInfo productAttributes) {
        return mapProductDao.replace(productAttributes.getProductId(), productAttributes);

    }

    public void delete(int id) {
        mapProductDao.remove(id);
    }

    public ProductInfo get(int id) {
        return mapProductDao.get(id);
    }

    public List<ProductInfo> getAll() {
        return new ArrayList<>(mapProductDao.values());
    }
}
