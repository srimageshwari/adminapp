package com.b2s.service.adminapp.controller;

import com.b2s.service.adminapp.model.Login;
import com.b2s.service.adminapp.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author smuthuvel 2019-01-03
 */


@Controller
public class UserController {

    private AdminService adminService;


    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/loginpage")
    public String welcome() {
        return "loginpage.html";
    }

   /* @PostMapping(value = "/welcome")
    public String loginProcess(@ModelAttribute("login") Login login, ModelMap map) {
        if (adminService.validateUser(login).equalsIgnoreCase("success")) {
            map.addAttribute("name", login.getUsername());
            return "redirect:navigation.html";
        } else {
            return "redirect:loginpage.html";
        }
    }*/

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public ModelMap saveStudent(@ModelAttribute("login") Login login, ModelMap map) {
        return map.addAttribute("msg", login.getUsername());

    }}







