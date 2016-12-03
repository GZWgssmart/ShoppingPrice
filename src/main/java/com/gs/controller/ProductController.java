package com.gs.controller;

import com.gs.bean.Product;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by WangGenshen on 5/16/16.
 */
@Controller
@RequestMapping("/pro")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Resource
    private ProductService productService;

    @RequestMapping(value = "list_page", method = RequestMethod.GET)
    public String toListPage() {
        return "product/search";
    }

    @ResponseBody
    @RequestMapping(value = "search_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Product> searchPager(@Param("page")String page, @Param("rows")String rows, @Param("keyword") String keyword) {
        List<Product> productList = productService.search(keyword);
        Pager4EasyUI<Product> pager = new Pager4EasyUI<Product>();
        pager.setRows(productList);
        pager.setTotal(productList.size());
        return pager;
    }

    @RequestMapping(value = "setting", method = RequestMethod.GET)
    public String settingPage() {
        return "product/setting";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
