package com.b2s.service.adminapp.service;


import com.b2s.service.adminapp.model.Login;
import com.b2s.service.adminapp.model.User;
import com.b2s.service.adminapp.model.UserInfo;

import java.util.List;


public interface AdminService {
   List<UserInfo> insert(List<UserInfo> userAttributes);
    UserInfo get(int userId);
    UserInfo update(int userId, UserInfo user);
    void delete(int userId);
    List<UserInfo> getAll();



}