package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.Shoes;
import com.anpai.shoesservice.entity.ShoesImage;
import com.anpai.shoesservice.entity.ShoesSort;
import com.anpai.shoesservice.entity.vo.ShoesImageQuery;
import com.anpai.shoesservice.entity.vo.ShoesSortQuery;
import com.anpai.shoesservice.service.ShoesImageService;
import com.anpai.shoesservice.service.ShoesSortService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api(tags = "鞋子分类管理")
@RestController
@RequestMapping("/shoesservice/shoes-sort")
public class ShoesSortController {
    @Autowired
    private ShoesSortService shoesSortService;


    @ApiOperation(value = "鞋子分类列表")
    @GetMapping("/findAll")
    public R findAllShoesSort(){
        List<ShoesSort> list = shoesSortService.list(null);
        return R.ok().data("items",list);

    }

    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeShoesSort(@ApiParam(name = "id", value = "鞋子分类id", required = true) @PathVariable Long id){
        boolean flag = shoesSortService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询鞋子分类")
    @PostMapping("/pageShoesSort/{current}/{limit}")
    public R pageShoesSort(@PathVariable Long current,
                            @PathVariable Long limit){
        // 常见page对象
        Page<ShoesSort> shoesSortPage = new Page<>(current, limit);

        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageShoes对象里面
        shoesSortService.page(shoesSortPage,null);

        long total = shoesSortPage.getTotal(); // 总记录数
        List<ShoesSort> records = shoesSortPage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "先条件再分页查询")
    @PostMapping("/pageShoesSortCondition/{current}/{limit}")
    public R pageShoesSortCondition(@PathVariable Long current,
                                     @PathVariable Long limit,
                                     @RequestBody(required = false) ShoesSortQuery shoesSortQuery){

        // 常见page对象
        Page<ShoesSort> shoesSortPage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<ShoesSort> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis 学过动态sql
        Integer shoesNumber = shoesSortQuery.getShoesNumber();
        Float shoesSize = shoesSortQuery.getShoesSize();
        String begin = shoesSortQuery.getBegin();
        String shoesColor = shoesSortQuery.getShoesColor();
        String end = shoesSortQuery.getEnd();

        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(shoesNumber)){
            wrapper.eq("shoesNumber",shoesNumber);
        }
        if(!StringUtils.isEmpty(shoesSize)){
            wrapper.eq("shoesSize",shoesSize);
        }
        if(!StringUtils.isEmpty(shoesColor)){
            wrapper.eq("shoesSize",shoesColor);
        }
        if(!StringUtils.isEmpty(begin)){
            //  ge 大于等于
            wrapper.ge("createTime",begin);
        }
        if(!StringUtils.isEmpty(end)){
            // le 小于等于
            wrapper.le("createTime",end);
        }



        // 排序
        wrapper.orderByDesc("createTime");

        // 调用方法实现条件查询分页
        shoesSortService.page(shoesSortPage,wrapper);
        long total = shoesSortPage.getTotal(); // 总记录数
        List<ShoesSort> records = shoesSortPage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 添加鞋子分类接口的方法
    @ApiOperation(value = "添加鞋子分类")
    @PostMapping("/addShoesSort")
    public R addShoesSort(@RequestBody ShoesSort shoesSort){
        boolean save = shoesSortService.save(shoesSort);
        if(save)
            return R.ok();
        return R.error();
    }

    // 根据鞋子分类ID进行查询
    @ApiOperation(value = "鞋子分类ID查询")
    @GetMapping("/getShoesSort/{id}")
    public R getShoesSort(@PathVariable String id){
        ShoesSort shoesSort = shoesSortService.getById(id);
        return R.ok().data("shoesSort",shoesSort);
    }

    // 鞋子分类信息修改功能
    @ApiOperation(value = "鞋子分类信息修改")
    @PostMapping("/updateShoesSort")
    public R updateShoesSort(@RequestBody ShoesSort shoesSort){
        boolean flag = shoesSortService.updateById(shoesSort);
        if(flag)
            return R.ok();
        return R.error();
    }

}

