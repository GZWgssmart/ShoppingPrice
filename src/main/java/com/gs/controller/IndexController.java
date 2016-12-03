package com.gs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by WangGenshen on 5/17/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String home(Model model) {
        return "product/home";
    }

    @RequestMapping(value = "redirect_index",method = RequestMethod.GET)
    public String redirectHome(Model model) {
        model.addAttribute("redirect", "redirect");
        return "product/home";
    }

}
