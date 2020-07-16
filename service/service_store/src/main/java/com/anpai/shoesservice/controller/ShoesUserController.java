package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.Shoes;
import com.anpai.shoesservice.entity.ShoesSort;
import com.anpai.shoesservice.entity.ShoesUser;
import com.anpai.shoesservice.entity.vo.ShoesQuery;
import com.anpai.shoesservice.entity.vo.ShoesUserQuery;
import com.anpai.shoesservice.service.ShoesUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xsz
 * @since 2020-06-13
 */
@CrossOrigin
@Api(tags = "鞋子（面向用户）管理")
@RestController
@RequestMapping("/shoesservice/shoes-user")
public class ShoesUserController {
    @Autowired
    ShoesUserService shoesUserService;

    @ApiOperation(value = "鞋子（面向用户）列表")
    @GetMapping("/findAll")
    public R findAll(){
        List<ShoesUser> list = shoesUserService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "条件查询再分页")
    @PostMapping("/pageCondition/{current}/{limit}")
    public R pageCondition(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody(required = false) ShoesUserQuery shoesUserQuery){
        // 常见page对象
        Page<ShoesUser> page = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<ShoesUser> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 学过动态sql
        String shoesUserName = shoesUserQuery.getShoesUserName();
        String shoesUserStore = shoesUserQuery.getShoesUserStore();
        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(shoesUserName)){
            wrapper.like("shoesUserName",shoesUserName);
        }
        if(!StringUtils.isEmpty(shoesUserStore)){
            wrapper.like("shoesUserStore",shoesUserStore);
        }
        // 排序
        wrapper.orderByDesc("shoesUserComment");
        // 调用方法实现条件查询分页
        shoesUserService.page(page,wrapper);
        long total = page.getTotal(); // 总记录数
        List<ShoesUser> records = page.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

