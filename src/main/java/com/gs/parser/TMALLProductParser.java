package com.gs.parser;

import com.gs.bean.Product;
import com.gs.common.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGenshen on 11/28/16.
 */
public class TMALLProductParser implements ProductParser {

    public List<Product> parserList(String searchKey, int count) {
        List<Product> products = new ArrayList<Product>();
        try {
            String searchUrl = Constants.TMALL_SEARCH_STR.replace(Constants.SEARCH_KEY, URLEncoder.encode(searchKey, "gb2312"));
            System.out.println("tm: " + searchUrl);
            Document doc = Jsoup.connect(searchUrl).get();
            Elements productEles = doc.getElementsByClass("product");
            if (productEles != null) {
                int size = productEles.size();
                for (int i = 0; i < count; i++) {
                    if (i < size){
                        Element productEle = productEles.get(i);
                        Elements imgWrap = productEle.getElementsByClass("product-iWrap").get(0).getElementsByClass("productImg-wrap");
                        if (imgWrap == null || imgWrap.size() == 0) {
                            continue;
                        }
                        Element productA = imgWrap.get(0).getElementsByTag("a").get(0);
                        String url = "https:" + productA.attr("href");
                        Element img = productA.getElementsByTag("img").get(0);
                        String imgUrl = img.attr("src");
                        if (imgUrl == null || imgUrl.equals("")) {
                            imgUrl = img.attr("data-ks-lazyload");
                        }
                        imgUrl = "https:" + imgUrl;
                        String salePrice = productEle.getElementsByClass("productPrice").get(0).getElementsByTag("em").get(0).text().substring(1);
                        String title = productEle.getElementsByClass("productTitle").get(0).getElementsByTag("a").get(0).attr("title");
                        String shop = productEle.getElementsByClass("productShop-name").get(0).text();
                        Product product = new Product();
                        product.setTitle(title);
                        product.setUrl(url);
                        product.setSalePrice(salePrice);
                        product.setSearchKey(searchKey);
                        product.setSearchUrl(searchUrl);
                        product.setImageUrl(imgUrl);
                        product.setPlatform(Constants.TMALL_PLATFORM);
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
