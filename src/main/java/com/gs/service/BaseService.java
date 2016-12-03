package com.gs.service;

import com.gs.common.bean.Pager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangGenshen on 5/25/16.
 */
public interface BaseService<T, PK extends Serializable> {

    public List<T> queryAll();
    public List<T> queryAll(String status);
    public T queryById(PK pk);
    public T query(T t);
    public int insert(T t);
    public int update(T t);
    public List<T> queryByPager(Pager pager);
    public int count();
    public int inactive(PK pk);
    public int active(PK pk);
    public int batchInsert(List<T> tList);
}
