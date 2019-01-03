package service

import com.b2s.service.adminapp.dao.MapProductDao
import com.b2s.service.adminapp.dao.ProductDao
import com.b2s.service.adminapp.model.Product
import com.b2s.service.adminapp.model.ProductInfo
import com.b2s.service.adminapp.model.ProductType
import com.b2s.service.adminapp.service.DefaultProductService
import com.b2s.service.adminapp.service.ProductValidator
import spock.lang.Specification
import spock.lang.Subject

class DefaultProductServiceSpec extends Specification {

    @Subject
    DefaultProductService defaultProductService;
    MapProductDao mapProductDao;
    ProductValidator productValidator;

    def setup() {
        mapProductDao = Mock(MapProductDao);
        productValidator = Mock(ProductValidator);
        defaultProductService = new DefaultProductService(productValidator,mapProductDao);
    }

    def "should collect specified product from repository"() {

        given:
        "Assigning expected values"
        ProductInfo user = ProductInfo.builder().setName("t&g").setPoints(50).setProductId(1).setType(ProductType.EVENT_TICKETS).build();
        mapProductDao.get(1) >> user;

        when:
        ProductInfo result = defaultProductService.get(1);

        then:
        user == result;
    }

    def "should collect specified product from list"() {

        given:
        "Assigning expected values"
        def output = [ProductInfo.builder().setName("t&g").setPoints(50).setProductId(1).setType(ProductType.MERCHANDISE)
                              .setDescription(Optional.empty()).build(),
                      ProductInfo.builder().setName("birthdayCards").setPoints(80).setProductId(2).
                              setType(ProductType.GIFT_CARDS).setDescription(Optional.empty()).build()];
        mapProductDao.getAll() >> output

        expect:
        def products = defaultProductService.getByType(points, type)
        products == result

        where:
        points | type          || result
        50     | "MERCHANDISE" || [new Product("t&g", 50, 1, ProductType.MERCHANDISE)]
        80     | "GIFT_CARDS"  || [new Product("birthdayCards", 80, 2, ProductType.GIFT_CARDS)]

    }
}














































