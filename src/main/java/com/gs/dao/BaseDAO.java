package com.gs.dao;

import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangGenshen on 5/16/16.
 */
public interface BaseDAO<T, PK extends Serializable> {

    public int insert(T t);
    public int batchInsert(List<T> list);
    public int delete(T t);
    public int deleteById(PK id);
    public int batchDelete(List<T> list);
    public int update(T t);
    public int batchUpdate(List<T> list);
    public List<T> queryAll();
    public List<T> queryAll(@Param("status") String status);
    public T query(T t);
    public T queryById(PK id);
    public List<T> queryByPager(Pager pager);
    public int count();
    public int inactive(String id);
    public int active(String id);

}
