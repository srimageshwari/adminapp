package com.b2s.service.adminapp.dao;

import com.b2s.service.adminapp.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author smuthuvel 2018-12-20
 */
@Component
public class MapAdminDao implements  AdminDao {

    Map<Integer, UserInfo> mapAdminDao = new HashMap<>();

    public Map<Integer, UserInfo> getMapAdminDao() {
        return mapAdminDao;
    }

    public List<UserInfo> insert(List<UserInfo> userAttributes) {
        for(UserInfo user:userAttributes)
        mapAdminDao.put(user.getUserId(), user);
        return getAll();
    }

    public UserInfo update(int id, UserInfo user) {
       return mapAdminDao.replace(id, user);
    }

    public void delete(int id) {
      mapAdminDao.remove(id);
    }

    public UserInfo get(int id) {
        return mapAdminDao.get(id);
    }

    public List<UserInfo> getAll() {
        return new ArrayList(mapAdminDao.values());
    }
}
