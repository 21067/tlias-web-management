package com.itheima.mapper;
import com.itheima.pojo.Dept;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有部门
    @Select("select * from dept")//mapper接口会把这条sql语句发送给数据库，再把查询出来的信息封装在部门集合中，最终把这个集合的数据返回给service又返回给controller，然后controller拿到返回数据再返回给前端
    List<Dept> list();

    //根据id删除部门
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //更新部门
    @Update("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);
}
