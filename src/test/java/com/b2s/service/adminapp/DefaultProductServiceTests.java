package com.b2s.service.adminapp;



import com.b2s.service.adminapp.dao.DefaultProductDao;
import com.b2s.service.adminapp.dao.MapProductDao;
import com.b2s.service.adminapp.dao.ProductDao;
import com.b2s.service.adminapp.model.ProductInfo;
import com.b2s.service.adminapp.service.DefaultProductService;
import com.b2s.service.adminapp.service.ProductValidator;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DefaultProductServiceTests {
    DefaultProductService defaultProductService;
     MapProductDao mapProductDao;
    ProductValidator productValidator;

    @Before
    public void setUp() throws Exception {
       mapProductDao = EasyMock.createMock(MapProductDao.class);
        defaultProductService = new DefaultProductService(productValidator,mapProductDao);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetProduct() throws Exception {

        ProductInfo user = ProductInfo.builder().build();
        EasyMock.expect(mapProductDao.get(1)).andReturn(user);
        EasyMock.replay(mapProductDao);
        Assert.assertEquals(user, defaultProductService.get(1));
        EasyMock.verify(mapProductDao);
    }

    @Test
    public void testUpdateProduct() throws Exception {

    }

    @Test
    public void testInsertproduct() throws Exception {

    }

    @Test
    public void testDeleteProduct() throws Exception {

    }

    @Test
    public void testGetAllProduct() throws Exception {

    }

    @Test
    public void testGetAllProductByType() throws Exception {

    }
}
