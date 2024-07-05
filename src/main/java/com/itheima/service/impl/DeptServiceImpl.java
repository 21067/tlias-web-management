package com.itheima.service.impl;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    //注入mapper
    @Autowired
    private DeptMapper deptMapper;//service并不能进行数据库操作，得调用mapper接口才能操作

    //查询全部部门
    @Override
    public List<Dept> list() {
        return deptMapper.list();//此处的list方法是mapper接口中的list方法，可以直接操作数据库
    }

    //删除部门
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    //增加部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }


    //修改部门
    @Override
    public void update(Dept dept, Integer id) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setId(id);
        deptMapper.update(dept);
    }
}