package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.AccountBook;
import com.anpai.shoesservice.entity.ShoesWarehouse;
import com.anpai.shoesservice.entity.vo.AccountBookQuery;
import com.anpai.shoesservice.entity.vo.ShoesWarehouseQuery;
import com.anpai.shoesservice.service.ShoesWarehouseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@Api(tags = "鞋库管理")
@RestController
@RequestMapping("/shoesservice/shoes-warehouse")
public class ShoesWarehouseController {
    @Autowired
    ShoesWarehouseService shoesWarehouseService;

    @ApiOperation(value = "所有仓库列表")
    @GetMapping("/findAll")
    public R findAllShoesWarehouse(){
        List<ShoesWarehouse> list = shoesWarehouseService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeShoesWarehouse(@ApiParam(name = "id", value = "仓库列表id", required = true) @PathVariable Long id){
        boolean flag = shoesWarehouseService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }

    // 条件查询再分页的方法
    @ApiOperation(value = "条件查询再分页")
    @PostMapping("/pageShoesWarehouseCondition/{current}/{limit}")
    public R pageShoesWarehouseCondition(@PathVariable Long current,
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) ShoesWarehouseQuery shoesWarehouseQuery){

        // 常见page对象
        Page<ShoesWarehouse> shoesWarehousePage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<ShoesWarehouse> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis 学过动态sql
        String shoesFactoryName = shoesWarehouseQuery.getShoesFactoryName();
        Integer shoesNumber = shoesWarehouseQuery.getShoesNumber();
        BigDecimal shoesUnitPrice = shoesWarehouseQuery.getShoesUnitPrice();
        Integer warehouseNumber = shoesWarehouseQuery.getWarehouseNumber();
        String begin = shoesWarehouseQuery.getBegin();
        String end = shoesWarehouseQuery.getEnd();


        // 判断条件值是否为空，如果不为空拼接条件

        if(!StringUtils.isEmpty(begin)){
            //  ge 大于等于
            wrapper.ge("createTime",begin);
        }
        if(!StringUtils.isEmpty(end)){
            // le 小于等于
            wrapper.le("createTime",end);
        }
        if(!StringUtils.isEmpty(shoesFactoryName)){
            // 构造条件
            wrapper.like("shoesFactoryName",shoesFactoryName);
        }
        if(!StringUtils.isEmpty(shoesNumber)){
            wrapper.eq("shoesNumber",shoesNumber);
        }
        if(!StringUtils.isEmpty(shoesUnitPrice)){
            wrapper.eq("shoesUnitPrice",shoesUnitPrice);
        }
        if(!StringUtils.isEmpty(warehouseNumber)){
            wrapper.eq("warehouseNumber",warehouseNumber);
        }

        // 排序
        wrapper.orderByDesc("createTime");


        // 调用方法实现条件查询分页
        shoesWarehouseService.page(shoesWarehousePage,wrapper);
        long total = shoesWarehousePage.getTotal(); // 总记录数
        List<ShoesWarehouse> records = shoesWarehousePage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 添加仓库记录接口的方法
    @ApiOperation(value = "添加仓库记录")
    @PostMapping("/addShoesWarehouse")
    public R addShoesWarehouse(@RequestBody ShoesWarehouse shoesWarehouse){
        boolean save = shoesWarehouseService.save(shoesWarehouse);
        if(save)
            return R.ok();
        return R.error();
    }

    // 根据仓库ID进行查询
    @ApiOperation(value = "仓库ID查询")
    @GetMapping("/getShoesWarehouse/{id}")
    public R getShoesWarehouse(@PathVariable String id){
        ShoesWarehouse shoesWarehouse = shoesWarehouseService.getById(id);
        return R.ok().data("shoesWarehouse",shoesWarehouse);
    }

    // 仓库信息修改功能
    @ApiOperation(value = "仓库信息修改")
    @PostMapping("/updateShoesWarehouse")
    public R updateShoesWarehouse(@RequestBody ShoesWarehouse shoesWarehouse){
        boolean flag = shoesWarehouseService.updateById(shoesWarehouse);
        if(flag)
            return R.ok();
        return R.error();
    }

}

