package com.gs.parser;

import com.gs.bean.Product;

import java.util.List;

/**
 * Created by WangGenshen on 11/28/16.
 */
public interface ProductParser {

    public List<Product> parserList(String searchKey, int count);

}
