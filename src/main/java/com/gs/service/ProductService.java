package com.gs.service;

import com.gs.bean.Product;
import com.gs.common.bean.Pager;

import java.util.List;

/**
 * Created by WangGenshen on 5/16/16.
 */
public interface ProductService extends BaseService<Product, String> {

    public List<Product> search(String keyword);

}
