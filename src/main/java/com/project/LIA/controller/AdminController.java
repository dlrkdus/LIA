package com.project.LIA.controller;

import com.project.LIA.domain.UserDomain;
import com.project.LIA.service.UserService;
import com.project.LIA.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;



    public AdminController(){
        System.out.println(getClass().getName() + "()생성");
    }
    @GetMapping("/main")
    public void main(Model model){
        UserDomain userDomain = U.getLoggedUser();
        if (userDomain != null){

            userDomain = userService.findById(userDomain.getId());
            model.addAttribute("profile_img", userDomain.getProfile_img());
        } else {
            model.addAttribute("profile_img", null);
        }
    }




}
