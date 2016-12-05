package com.gs.bean;

/**
 * Created by WangGenshen on 5/16/16.
 */
public class Product {

    private String searchKey;
    private String title;
    private String platform;
    private String seller;
    private double originalPrice;
    private String salePrice;
    private String searchUrl;
    private String url;
    private int saleCount;
    private int commentCount;
    private String imageUrl;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "searchKey='" + searchKey + '\'' +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", seller='" + seller + '\'' +
                ", originalPrice=" + originalPrice +
                ", salePrice=" + salePrice +
                ", searchUrl='" + searchUrl + '\'' +
                ", url='" + url + '\'' +
                ", saleCount=" + saleCount +
                ", commentCount=" + commentCount +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
