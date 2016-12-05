package com.gs.parser;

import com.gs.bean.Product;
import com.gs.common.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGenshen on 11/28/16.
 */
public class SUNINGProductParser implements ProductParser {

    private String[] getIDVendor(String imgUrl) {
        System.out.println(imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.lastIndexOf("_") - 2));
        return imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.lastIndexOf("_") - 2).split("-");
    }

    private String getPrice(String imgUrl) {
        String[] idVendor = getIDVendor(imgUrl);
        String vendor = idVendor[0];
        String id = idVendor[1].substring(9);
        System.out.println("id: " + id + ", vendor: " + vendor);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(Constants.SUNING_PRICE_STR.replace("{id}", id).replace("{vendor}", vendor));
        get.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36");
        try {
            HttpResponse resp = client.execute(get);
            String priceStr = EntityUtils.toString(resp.getEntity());
            System.out.println(priceStr);
            int begin = priceStr.indexOf("promotionPrice") + "promotionPrice\":\"".length();
            int end = priceStr.indexOf("\"bookPrice\"") - 2;
            return priceStr.substring(begin, end);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> parserList(String searchKey, int count) {
        List<Product> products = new ArrayList<Product>();
        try {
            String searchUrl = Constants.SUNING_SEARCH_STR.replace(Constants.SEARCH_KEY, searchKey);
            System.out.println("sn: " + searchUrl);
            Document doc = Jsoup.connect(searchUrl).header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36").get();
            Elements productEles = doc.getElementsByClass("wrap");
            if (productEles != null) {
                int size = productEles.size();
                for (int i = 0; i < count; i++) {
                    if (i < size) {
                        Element productEle = productEles.get(i);
                        Element productA = productEle.getElementsByClass("img-block").get(0).getElementsByTag("a").get(0);
                        String url = productA.attr("href");
                        String imgUrl = "http:" + productA.getElementsByTag("img").get(0).attr("src2");
                        String title = productEle.getElementsByClass("sell-point").get(0).getElementsByTag("a").get(0).text();
                        String shop = productEle.getElementsByClass("seller").get(0).attr("salesname");
                        Product product = new Product();
                        product.setTitle(title);
                        product.setUrl(url);
                        product.setSalePrice(getPrice(imgUrl));
                        System.out.println(product.getSalePrice());
                        product.setSearchKey(searchKey);
                        product.setSearchUrl(searchUrl);
                        product.setImageUrl(imgUrl);
                        product.setPlatform(Constants.SUNING_PLATFORM);
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
