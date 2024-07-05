package com.itheima.controller;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;//在controller中注入一个部门管理的service

    //查询部门数据
    @GetMapping(value = "/depts")
    public Result list(){
        log.info("查询全部部门数据");

        //调用service中的list方法来查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //删除部门操作
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }

    //新增部门操作
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //修改部门操作
    @PutMapping("/depts/{id}")
    public Result update(@RequestBody Dept dept,@PathVariable Integer id){
        log.info("修改部门编号:{}",id);
        deptService.update(dept,id);
        return Result.success();
    }
}
