package com.b2s.service.adminapp.dao;



import com.b2s.service.adminapp.model.UserInfo;

import java.util.List;

public interface AdminDao {

    List<UserInfo> insert(List<UserInfo> userAttributes);
    UserInfo get(int userId);
    List<UserInfo> getAll();
    UserInfo update(int userId, UserInfo user);
  void  delete(int userId);



}