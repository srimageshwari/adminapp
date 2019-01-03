package com.b2s.service.adminapp.controller;


import com.b2s.service.adminapp.model.UserInfo;
import com.b2s.service.adminapp.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public List<UserInfo> create(@RequestBody final List<UserInfo> userAttributes) {
        return adminService.insert(userAttributes);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/users/{userId}")
    public UserInfo get(@PathVariable final int userId) {
        return adminService.get(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{userId}")
    public ResponseEntity update(@PathVariable("userId") final int userId, @RequestBody final UserInfo user) {
        UserInfo updated = adminService.update(userId, user);
        return ResponseEntity.ok(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity delete(@PathVariable final int userId) {
        adminService.delete(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/users")
    public List<UserInfo> getAll() {
        return adminService.getAll();
    }
}
















/*  @PostMapping(value="/admin/createuser/")

    public UserInfo createuser(@RequestBody UserInfo userAttributes)
    {
        return adminService.createusertoservice(userAttributes);
    }


    @GetMapping(value="/admin/{id}")
    public UserInfo viewuser(@PathVariable int id)
    {
        return adminService.getuserAttributes(id);
    }*/


    /*@RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
    public void updateuser(@PathVariable("id") int userid, @ModelAttribute UserInfo userAttributes) {

      adminService.updateUser(userid, userAttributes);
    }*/
