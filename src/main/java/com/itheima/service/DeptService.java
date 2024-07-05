package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

//部门业务规则
public interface DeptService {
    //查询全部部门数据
    List<Dept> list();

    //delete：删除部门
    void delete(Integer id);

    //新增部门
    void add(Dept dept);

    //修改部门信息
    void update(Dept dept,Integer id);
}