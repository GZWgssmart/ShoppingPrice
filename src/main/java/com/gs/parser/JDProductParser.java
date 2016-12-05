package com.gs.parser;

import com.gs.bean.Product;
import com.gs.common.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGenshen on 11/28/16.
 */
public class JDProductParser implements ProductParser {

    public List<Product> parserList(String searchKey, int count) {
        List<Product> products = new ArrayList<Product>();
        String searchUrl = Constants.JD_SEARCH_STR.replace(Constants.SEARCH_KEY, searchKey);
        System.out.println("jd: " + searchUrl);
        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Elements productEles = doc.getElementsByClass("gl-i-wrap");
            if (productEles != null) {
                int size = productEles.size();
                for (int i = 0; i < count; i++) {
                    if (i < size) {
                        Element productEle = productEles.get(i);
                        Element productA = productEle.getElementsByClass("p-img").get(0).getElementsByTag("a").get(0);
                        String url = "https:" + productA.attr("href").substring(2);
                        String imgUrl = "https:" + productA.getElementsByTag("img").get(0).attr("src");
                        String salePrice = productEle.getElementsByClass("p-price").get(0).getElementsByTag("i").get(0).text();
                        String title = productEle.getElementsByClass("p-name").get(0).getElementsByTag("a").get(0).attr("title");
                        // String shop = productEle.getElementsByClass("productShop-name").get(0).text();
                        Product product = new Product();
                        product.setTitle(title);
                        product.setUrl(url);
                        product.setSalePrice(salePrice);
                        product.setSearchKey(searchKey);
                        product.setSearchUrl(searchUrl);
                        product.setImageUrl(imgUrl);
                        product.setPlatform(Constants.JD_PLATFORM);
                        // product.setSeller(shop);
                        products.add(product);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
