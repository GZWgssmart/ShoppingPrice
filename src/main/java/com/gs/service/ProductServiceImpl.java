package com.gs.service;

import com.gs.bean.Product;
import com.gs.common.Constants;
import com.gs.common.bean.Pager;
import com.gs.common.util.Config;
import com.gs.dao.ProductDAO;
import com.gs.parser.GOMEProductParser;
import com.gs.parser.JDProductParser;
import com.gs.parser.SUNINGProductParser;
import com.gs.parser.TMALLProductParser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGenshen on 5/16/16.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDAO productDAO;

    @Override
    public List<Product> queryAll() {
        return productDAO.queryAll();
    }

    @Override
    public List<Product> queryAll(String status) {
        return productDAO.queryAll(status);
    }

    @Override
    public Product queryById(String id) {
        return productDAO.queryById(id);
    }

    @Override
    public Product query(Product product) {
        return productDAO.query(product);
    }

    @Override
    public int insert(Product product) {
        return productDAO.insert(product);
    }

    @Override
    public int update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public int inactive(String id) {
        return productDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return productDAO.active(id);
    }

    @Override
    public int batchInsert(List<Product> products) {
        return productDAO.batchInsert(products);
    }

    @Override
    public List<Product> queryByPager(Pager pager) {
        return productDAO.queryByPager(pager);
    }

    @Override
    public int count() {
        return productDAO.count();
    }

    @Override
    public List<Product> search(String keyword) {
        List<Product> products = new ArrayList<Product>();
        if (keyword != null) {
            Config config = new Config();
            config.build("classpath:/conf/search.properties");
            int count = config.getInt(Constants.SEARCH_COUNT);
            TMALLProductParser tmallProductParser = new TMALLProductParser();
            List<Product> tmList = tmallProductParser.parserList(keyword, count);
            if (tmList != null && tmList.size() > 0) {
                products.addAll(tmList);
            }
            JDProductParser jdProductParser = new JDProductParser();
            List<Product> jdList = jdProductParser.parserList(keyword, count);
            if (jdList != null && jdList.size() > 0) {
                products.addAll(jdList);
            }
            SUNINGProductParser suningProductParser = new SUNINGProductParser();
            List<Product> snList = suningProductParser.parserList(keyword, count);
            if (snList != null && snList.size() > 0) {
                products.addAll(snList);
            }
            GOMEProductParser gomeProductParser = new GOMEProductParser();
            List<Product> gmList = gomeProductParser.parserList(keyword, count);
            if (gmList != null && gmList.size() > 0) {
                products.addAll(gmList);
            }
        }
        return products;
    }
}
