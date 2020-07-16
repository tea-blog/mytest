package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.Shoes;
import com.anpai.shoesservice.entity.ShoesImage;
import com.anpai.shoesservice.entity.vo.ShoesImageQuery;
import com.anpai.shoesservice.entity.vo.ShoesQuery;
import com.anpai.shoesservice.service.ShoesImageService;
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
@Api(tags = "鞋子图片管理")
@RestController
@RequestMapping("/shoesservice/shoes-image")
public class ShoesImageController {
    @Autowired
    private ShoesImageService shoesImageService;


    @ApiOperation(value = "鞋子图片列表")
    @GetMapping("/findAll")
    public R findAllShoesImage(){
        List<ShoesImage> list = shoesImageService.list(null);
        return R.ok().data("items",list);

    }

    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeShoesImage(@ApiParam(name = "id", value = "鞋子图片id", required = true) @PathVariable Long id){
        boolean flag = shoesImageService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }

    @ApiOperation(value = "分页查询鞋子照片")
    @PostMapping("/pageShoesImage/{current}/{limit}")
    public R pageShoesImage(@PathVariable Long current,
                           @PathVariable Long limit){
        // 常见page对象
        Page<ShoesImage> shoesImagePage = new Page<>(current, limit);

        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageShoes对象里面
        shoesImageService.page(shoesImagePage,null);

        long total = shoesImagePage.getTotal(); // 总记录数
        List<ShoesImage> records = shoesImagePage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "条件查询再分页")
    @PostMapping("/pageShoesImageCondition/{current}/{limit}")
    public R pageShoesImageCondition(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody(required = false) ShoesImageQuery shoesImageQuery){

        // 常见page对象
        Page<ShoesImage> shoesImagePage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<ShoesImage> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis 学过动态sql
        System.out.println(shoesImageQuery.getShoesNumber());
        Integer shoesNumber = shoesImageQuery.getShoesNumber();
        String begin = shoesImageQuery.getBegin();
        String end = shoesImageQuery.getEnd();


        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(begin)){
            //  ge 大于等于
            wrapper.ge("createTime",begin);
        }
        if(!StringUtils.isEmpty(end)){
            // le 小于等于
            wrapper.le("createTime",end);
        }
        if(!StringUtils.isEmpty(shoesNumber)){
            wrapper.eq("shoesNumber",shoesNumber);
        }
        // 排序
        wrapper.orderByDesc("createTime");

        // 调用方法实现条件查询分页
        shoesImageService.page(shoesImagePage,wrapper);
        long total = shoesImagePage.getTotal(); // 总记录数
        List<ShoesImage> records = shoesImagePage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 添加鞋子照片接口的方法
    @ApiOperation(value = "添加鞋子照片")
    @PostMapping("/addShoesImage")
    public R addShoesImage(@RequestBody ShoesImage shoesImage){
        boolean save = shoesImageService.save(shoesImage);
        if(save)
            return R.ok();
        return R.error();
    }
    // 根据鞋子照片ID进行查询
    @ApiOperation(value = "鞋子照片ID查询")
    @GetMapping("/getShoesImage/{id}")
    public R getShoesImage(@PathVariable String id){
        ShoesImage shoesImage = shoesImageService.getById(id);
        return R.ok().data("shoesImage",shoesImage);
    }

    // 鞋子照片信息修改功能
    @ApiOperation(value = "鞋子照片信息修改")
    @PostMapping("/updateShoesImage")
    public R updateShoesImage(@RequestBody ShoesImage shoesImage){
        boolean flag = shoesImageService.updateById(shoesImage);
        if(flag)
            return R.ok();
        return R.error();
    }
}

