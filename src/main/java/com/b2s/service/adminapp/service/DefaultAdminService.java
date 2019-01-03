package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.dao.AdminDao;
import com.b2s.service.adminapp.dao.JdbcAdminDao;
import com.b2s.service.adminapp.exception.NotFoundException;
import com.b2s.service.adminapp.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author smuthuvel 2018-12-14
 */
@Service
public class DefaultAdminService  implements AdminService {

    private AdminDao adminDao;
    private final UserValidator userValidator;

    public DefaultAdminService(final JdbcAdminDao adminDao, final UserValidator userValidator) {
        this.adminDao = adminDao;
        this.userValidator = userValidator;
    }

    public List<UserInfo> insert(List<UserInfo> userInfo) {
       // userValidator.validateAttributes(userInfo);
        return adminDao.insert(userInfo);
    }

    public UserInfo get(int userId) {
        if (userId == 0)
            throw new NotFoundException("provide valid UserId");
        else
            return adminDao.get(userId);
    }

    public List<UserInfo> getAll() {
        return adminDao.getAll();
    }

    public UserInfo update(int userId, UserInfo user) {
        userValidator.validateUpdateAttributes(user);
       adminDao.update(userId, user);
        return UserInfo.builder().setName(user.getName()).setAddress(user.getAddress()).setCity(user.getCity())
                .setUserId(userId).build();
    }

    public void delete(int userId) {
       adminDao.delete(userId);

    }
}
