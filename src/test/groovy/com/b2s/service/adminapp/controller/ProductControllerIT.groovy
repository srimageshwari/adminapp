package com.b2s.service.adminapp.controller

import com.b2s.service.adminapp.AdminApplication
import com.b2s.service.adminapp.dao.MapProductDao
import com.b2s.service.adminapp.model.ProductInfo
import com.b2s.service.adminapp.model.ProductType
import io.restassured.RestAssured
import io.restassured.parsing.Parser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author smuthuvel 2018-12-20
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [AdminApplication.class])
@ActiveProfiles('local')
class ProductControllerIT extends Specification {

    @Autowired
    private MapProductDao mapProductDao;

    @LocalServerPort
    int localServerPort

    def setup() {
        RestAssured.port = localServerPort
    }

    def cleanup() {
        RestAssured.reset()
    }


    def "CreateProduct"() {
        when:
        def result = RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ProductInfo.builder().setProductId(1).setName("levis").setPoints(100).setType(ProductType.GIFT_CARDS)
                .build())
                .post("/products")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .body()
                .as(ProductInfo.class)

        then:
        result.name == "levis"

    }

    def "getProduct"() {

        given:
        ProductInfo product =
                ProductInfo.builder().setName("levis").setPoints(150).setProductId(1).setType(ProductType.MERCHANDISE)
                        .setDescription(Optional.empty()).build()

        mapProductDao.getMapProductDao().put(1, product);

        when:
        def body = RestAssured.given()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("userId", 1)
                .when()
                .get("/products/{userId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()


        then:
        def result = body.as(ProductInfo)
        result.name == product.name

    }

    def "DeleteProduct"() {

        expect:
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("productId", 1)
                .delete("/products/{productId}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
    }

    def "listProduct"() {

        given:
        def productList =
                [ProductInfo.builder().setName("levis").setPoints(110).setProductId(1).setType(ProductType.GIFT_CARDS).
                         setDescription(Optional.empty()).build(),
                 ProductInfo.builder().setName("marriage").setPoints(110).setProductId(2).setType(ProductType.GIFT_CARDS).
                         setDescription(Optional.empty()).build()]

        mapProductDao.getMapProductDao().put(1, ProductInfo.builder().setName("levis").setPoints(110).setProductId(1).setType(ProductType.GIFT_CARDS).
                setDescription(Optional.empty()).build())
        mapProductDao.getMapProductDao().put(2, ProductInfo.builder().setName("marriage").setPoints(110).setProductId(2).setType(ProductType.GIFT_CARDS).
                setDescription(Optional.empty()).build())


        when:
        def result = RestAssured.given()
                .when()
                .get("/products")
                .then()
                .extract()
                .jsonPath()
                .getList("", ProductInfo.class)

        then:
        result == productList
    }

    def "listProductByType"() {

        given:
        def expected = [ProductInfo.builder().setName("levis").setPoints(110).setProductId(1).
                                setType(ProductType.GIFT_CARDS).setDescription(Optional.empty()).build(),
                        ProductInfo.builder().setName("marriage").setPoints(110).setProductId(2).
                                setType(ProductType.GIFT_CARDS).setDescription(Optional.empty()).build()];



        mapProductDao.getMapProductDao().put(1, ProductInfo.builder().setName("levis").setPoints(110).setProductId(1).setType(ProductType.GIFT_CARDS).
                setDescription(Optional.empty()).build())
        mapProductDao.getMapProductDao().put(2, ProductInfo.builder().setName("marriage").setPoints(110).setProductId(2).setType(ProductType.GIFT_CARDS).
                setDescription(Optional.empty()).build())


        when:
        def result = RestAssured.given()
                .when()
                .pathParams("points", 110, "type", "GIFT_CARDS")
                .get("/products/{points}/{type}")
                .then()
                .extract()
                .jsonPath()
                .getList("", ProductInfo.class)

        then:
        result == expected
    }
}
