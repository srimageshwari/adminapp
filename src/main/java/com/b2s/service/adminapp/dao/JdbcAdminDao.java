package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.UserInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class JdbcAdminDao implements AdminDao {

    String insertQuery = "INSERT INTO Employees(id,password,name,address,city,state,zip)VALUES(:userId,:password,:name," +
            ":address,:city,:state,:zip)";
    String updateQuery = "UPDATE   Employees SET name =:name,address =:address,city =:city  WHERE id=:userId";
    String deleteQuery = "DELETE from  Employees WHERE id= :userId";
    String getQuery = "SELECT * FROM  Employees WHERE id =:userId";
    String listQuery = "SELECT * FROM  Employees";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcAdminDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<UserInfo> insert(List<UserInfo> userAttributes) {
        List<Map<String, Object>> batchValues = new ArrayList<>(userAttributes.size());
        batchValues.addAll(userAttributes
                .stream().map(user -> new MapSqlParameterSource(
                        "userId", user.getUserId())
                        .addValue("password", user.getPassword())
                        .addValue("name", user.getName())
                        .addValue("address", user.getAddress())
                        .addValue("city", user.getCity())
                        .addValue("state", user.getState())
                        .addValue("zip", user.getZip()).getValues()).collect(Collectors.toList()));
        namedParameterJdbcTemplate.batchUpdate(insertQuery, batchValues.toArray(new Map[userAttributes.size()]));
        return userAttributes;
    }

    public UserInfo get(int userId) {
        SqlParameterSource parameter = new MapSqlParameterSource("userId", userId);
        return (UserInfo) namedParameterJdbcTemplate.queryForObject(getQuery, parameter, new UserRowMapper());
    }

    public UserInfo update(int userId, UserInfo user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("userId", userId)
                .addValue("name", user.getName())
                .addValue("address", user.getAddress())
                .addValue("city", user.getCity());
        namedParameterJdbcTemplate.update(updateQuery, parameterSource);
        return user;
    }

    public void delete(int userId) {
        SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
        namedParameterJdbcTemplate.update(deleteQuery, parameters);
    }

    public List<UserInfo> getAll() {
        return namedParameterJdbcTemplate.query(listQuery, new UserRowMapper());
    }
}














































/*  public UserInfo createusertodb(UserInfo userAttributes)
      {

          try {

              System.out.println("Connecting to database...");

              PreparedStatement stmt =dataBaseConnection.getConnection().prepareStatement("insert into usertable values(?,?,?,?,?,?,?)");
              stmt.setInt(1, userAttributes.getProductId());
              stmt.setString(2, userAttributes.getPassword());
              stmt.setString(3, userAttributes.getName());
              stmt.setString(4, userAttributes.getAddress());
              stmt.setString(5, userAttributes.getCity());
              stmt.setString(6, userAttributes.getState());
              stmt.setInt(7, userAttributes.getZip());
              System.out.println("Created table in given database...");
              int rs=stmt.executeUpdate();
              stmt.close();
              return userAttributes;
          }catch(SQLException se){

              se.printStackTrace();
          }catch(exception e) {

              e.printStackTrace();
          }
          throw new UserNotFoundException("cant create");
      }

      public UserInfo callbyid(int userid)
      {
          UserInfo userAttributes= null;
          try {

              System.out.println("Connecting to database...");

              PreparedStatement stmt =dataBaseConnection.getConnection().prepareStatement(("select * from Usertable where id= ?"));

              stmt.setInt(1,userid);
              ResultSet rs = stmt.executeQuery();
              while(rs.next())

              {  userAttributes =new UserInfo();
                  userAttributes.setProductId((rs.getInt("id")));
                  userAttributes.setPassword((rs.getString("password")));
                  userAttributes.setName((rs.getString("name")));
                  userAttributes.setAddress((rs.getString("address")));
                  userAttributes.setCity((rs.getString("city")));
                  userAttributes.setState((rs.getString("state")));
                  userAttributes.setZip((rs.getInt("zip")));
              }

              stmt.close();

              //return userAttributes;
          } catch (exception e) {

              e.printStackTrace();
          }

          if (userAttributes == null) {
              throw new UserNotFoundException("id not found");
          }

          return userAttributes;

      }}*/