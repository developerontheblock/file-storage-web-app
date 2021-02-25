package com.example.demo.controller;

import java.security.Principal;

import com.example.demo.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is home page!");
        return "homePage";
    }

    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public String publisherPage(Model model, Principal principal) {

        User loggedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedUser);
        model.addAttribute("userInfo", userInfo);

        return "publisherPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        // String userName = principal.getName();

       // System.out.println("User Name: " + userName);

        User loggedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loggedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loggedUser);
            model.addAttribute("userInfo", userInfo);

            String message = "Hi, " + principal.getName() + "!"
                    + "<br> You do not have permission to access this page! :(";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
}
