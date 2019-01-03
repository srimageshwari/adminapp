package com.b2s.service.adminapp.dao;


import com.b2s.service.adminapp.model.ProductInfo;
import com.b2s.service.adminapp.model.ProductType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductRowMapper implements RowMapper {
    public ProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

      return ProductInfo.builder().setName(rs.getString("name"))
                .setPoints(rs.getInt("points")).setProductId(rs.getInt("id")).
                      setType(ProductType.valueOf(rs.getString("type")))
              .setDescription(Optional.ofNullable(rs.getString("description"))).build();

    }

}

