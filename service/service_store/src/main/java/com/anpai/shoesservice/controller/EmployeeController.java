package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.Employee;
import com.anpai.shoesservice.entity.Shoes;
import com.anpai.shoesservice.entity.ShoesImage;
import com.anpai.shoesservice.service.EmployeeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-06
 */
@CrossOrigin
@Api(tags = "员工管理")
@RestController
@RequestMapping("/shoesservice/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @ApiOperation(value = "所有员工列表")
    @GetMapping("/findAll")
    public R findAllEmployee(){
        List<Employee> list = employeeService.list(null);
        return R.ok().data("items",list);

    }

    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeEmployee(@ApiParam(name = "id", value = "员工id", required = true) @PathVariable Long id){
        boolean flag = employeeService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询员工")
    @PostMapping("/pageEmployee/{current}/{limit}")
    public R pageEmployee(@PathVariable Long current,
                            @PathVariable Long limit){
        // 常见page对象
        Page<Employee> employeePage = new Page<>(current, limit);

        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageShoes对象里面
        employeeService.page(employeePage,null);

        long total = employeePage.getTotal(); // 总记录数
        List<Employee> records = employeePage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


    // 添加员工的接口
    @ApiOperation(value = "添加员工")
    @PostMapping("/addEmployee")
    public R addEmployee(@RequestBody Employee employee){
        boolean save = employeeService.save(employee);
        if(save)
            return R.ok();
        return R.error();
    }
    // 根据员工ID进行查询
    @ApiOperation(value = "员工ID查询")
    @GetMapping("/getEmployee/{id}")
    public R getEmployee(@PathVariable String id){
        Employee employee = employeeService.getById(id);
        return R.ok().data("employee",employee);
    }

    // 员工信息修改功能
    @ApiOperation(value = "员工信息修改")
    @PostMapping("/updateEmployee")
    public R updateEmployee(@RequestBody Employee employee){
        boolean flag = employeeService.updateById(employee);
        if(flag)
            return R.ok();
        return R.error();
    }

}

