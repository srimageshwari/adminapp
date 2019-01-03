package com.b2s.service.adminapp.dao;
//import com.example.adminapp.DBconnection.DBConnection;

import com.b2s.service.adminapp.model.ProductInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class DefaultProductDao  {
    String insertQuery = "INSERT INTO Product(name,points,type)VALUES(:name,:points,:type)";
    String updateQuery = "UPDATE Product set name =:name,points =:points,type =:type  where id=:productId";
    String deleteQuery = "DELETE from Product WHERE id= :productId";
    String getQuery = "SELECT * FROM Product WHERE id =:productId";
    String listQuery = "SELECT * FROM Product";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DefaultProductDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int updateProduct(int id, ProductInfo productAttributes) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("productId",id)
                .addValue("name", productAttributes.getName())
                .addValue("points", productAttributes.getPoints())
                .addValue("type", productAttributes.getType().name())
                .addValue("description", productAttributes.getDescription());
      return namedParameterJdbcTemplate.update(updateQuery,parameterSource);
    }

    public ProductInfo get(int id) {
        SqlParameterSource parameters = new MapSqlParameterSource("productId",id);
        return (ProductInfo) namedParameterJdbcTemplate.
                queryForObject(getQuery, parameters, new ProductRowMapper());
    }

    public ProductInfo insert(ProductInfo productAttributes) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", productAttributes.getName())
                .addValue("points", productAttributes.getPoints())
                .addValue("type", productAttributes.getType().name())
                .addValue("description", productAttributes.getDescription());
        namedParameterJdbcTemplate.update(insertQuery, parameters, keyholder, new String[]{"productId"});
        Number key = keyholder.getKey();
        return get(key.intValue());
    }

    public void delete(int id) {
        SqlParameterSource parameters = new MapSqlParameterSource("productId", id);
        namedParameterJdbcTemplate.update(deleteQuery, parameters);
    }

    public List<ProductInfo> getAll () {
        return namedParameterJdbcTemplate.query(listQuery, new ProductRowMapper());
    }}

































/*  public ProductInfo createproduct(ProductInfo productAttributes) {
        try {

            System.out.println("Connecting to database...");

            PreparedStatement stmt = dbConnection.getConnection().prepareStatement("insert into producttable values(?,?,?)");


            stmt.setString(1, productAttributes.getName());
            stmt.setInt(2, productAttributes.getPoints());
            stmt.setInt(3, productAttributes.getProductId());

            System.out.println("Created table in given database...");
            int rs = stmt.executeUpdate();
            stmt.close();
            return productAttributes;
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (exception e) {

            e.printStackTrace();
        }
        throw new UserNotFoundException("cant create");
    }


    public ProductInfo callbyid(int userid) {
        ProductInfo productAttributes = new ProductInfo();
        try {

            System.out.println("Connecting to database...");

            PreparedStatement stmt = dbConnection.getConnection().prepareStatement(("select * from Producttable where userid= ?"));

            stmt.setInt(1, userid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productAttributes.setName((rs.getString("name")));
                productAttributes.setPoints((rs.getInt("points")));
                productAttributes.setProductId((rs.getInt("Userid")));
            }
            stmt.close();

            return productAttributes;
        } catch (exception e) {

            e.printStackTrace();
        }

        if (productAttributes == null) {
            throw new UserNotFoundException("id not found");
        }
        return productAttributes;

    }*/