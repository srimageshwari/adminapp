package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.exception.InvalidRequestException;
import com.b2s.service.adminapp.model.User;
import com.b2s.service.adminapp.model.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @author smuthuvel 2018-12-14
 */
@Component
public class UserValidator {
    public void validateAttributes(UserInfo userInfo) {
        if (userInfo.getPassword() == null) {
            throw new InvalidRequestException("enter the password");
        }
        if (userInfo.getName() == null) {
            throw new InvalidRequestException("Enter the name");
        }
    }

    public void validateUpdateAttributes(UserInfo user) {
        if (user.getAddress() == null) {
            throw new InvalidRequestException("enter the Address");
        }
        if (user.getName() == null) {
            throw new InvalidRequestException("Enter the name");
        }
    }
}
