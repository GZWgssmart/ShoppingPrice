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
public class GOMEProductParser implements ProductParser {

    public List<Product> parserList(String searchKey, int count) {
        List<Product> products = new ArrayList<Product>();
        String searchUrl = Constants.GOME_SEARCH_STR.replace(Constants.SEARCH_KEY, searchKey);
        System.out.println("gm: " + searchUrl);
        try {
            Document doc = Jsoup.connect(searchUrl).get();
            Elements productEles = doc.getElementsByClass("product-item");
            if (productEles != null) {
                int size = productEles.size();
                for (int i = 0; i < count; i++) {
                    if (i < size) {
                        Element productLi = productEles.get(i);
                        Element productEle = productLi.getElementsByClass("item-tab-warp").get(0);
                        Element productInfo = productLi.getElementsByClass("productInfo").get(0);
                        String price = productInfo.attr("price");
                        Element productA = productEle.getElementsByClass("item-pic").get(0).getElementsByTag("a").get(0);
                        String url = productA.attr("href");
                        String imgUrl = productA.getElementsByTag("img").get(0).attr("gome-src");
                        String title = productEle.getElementsByClass("item-name").get(0).getElementsByTag("a").get(0).attr("title");
                        String shop = productEle.getElementsByClass("item-shop").get(0).getElementsByTag("span").text();
                        Product product = new Product();
                        product.setTitle(title);
                        product.setUrl(url);
                        product.setSalePrice(price);
                        product.setSearchKey(searchKey);
                        product.setSearchUrl(searchUrl);
                        product.setImageUrl(imgUrl);
                        product.setPlatform(Constants.GOME_PLATFORM);
                        product.setSeller(shop);
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
