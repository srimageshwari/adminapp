package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper {
    public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new UserInfo.UserAttributesBuilder().setUserId(rs.getInt("id"))
                .setPassword(rs.getString("password")).setName(rs.getString("name")).setAddress(rs.getString("address"))
                .setCity(rs.getString("city")).setState(rs.getString("state")).setZip(rs.getInt("zip")).build();

    }
}


