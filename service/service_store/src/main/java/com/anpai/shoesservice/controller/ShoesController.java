package com.anpai.shoesservice.controller;
import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.Shoes;
import com.anpai.shoesservice.entity.vo.ShoesQuery;
import com.anpai.shoesservice.service.ShoesService;
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
 * @author xsz
 * @since 2020-06-06
 */
@CrossOrigin
@Api(tags = "鞋子管理")
@RestController
@RequestMapping("/shoesservice/shoes")
public class ShoesController {
    // 访问地址： http://localhost:8001/shoesservice/shoes/findAll
    // 把service注入
    @Autowired
    private ShoesService shoesService;
    // 1  查询鞋子所有数据
    // rest风格
    @ApiOperation(value = "所有鞋子列表")
    @GetMapping("/findAll")
    public R findAllShoes(){
        List<Shoes> list = shoesService.list(null);
        return R.ok().data("items",list);

    }
    // 2 逻辑删除鞋子的方法
    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeShoes(@ApiParam(name = "id", value = "鞋子ID", required = true) @PathVariable Long id){
        boolean flag = shoesService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }
    // 3 分页查询鞋子的方法
    @ApiOperation(value = "分页查询鞋子")
    @PostMapping("/pageShoes/{current}/{limit}")
    public R pageListShoes(@PathVariable Long current,
                           @PathVariable Long limit){
        // 常见page对象
        Page<Shoes> shoesPage = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageShoes对象里面
        shoesService.page(shoesPage,null);
        long total = shoesPage.getTotal(); // 总记录数
        List<Shoes> records = shoesPage.getRecords(); // 数据list集合
        // Map map = new HashMap();
        // map.put("total",total);
        // map.put("rows",records);
        // return R.ok().data(map);
        return R.ok().data("total",total).data("rows",records);
    }
    // 4 条件查询再分页的方法
    @ApiOperation(value = "条件查询再分页")
    @PostMapping("/pageShoesCondition/{current}/{limit}")
    public R pageShoesCondition(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody(required = false) ShoesQuery shoesQuery){
        // 常见page对象
        Page<Shoes> pageShoes = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<Shoes> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 学过动态sql
        String name = shoesQuery.getName();
        Integer season = shoesQuery.getSeason();
        Integer type = shoesQuery.getType();
        Integer warehouseNumber = shoesQuery.getWarehouseNumber();
        String begin = shoesQuery.getBegin();
        String end = shoesQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){
            // 构造条件
            wrapper.like("shoesName",name);
        }
        if(!StringUtils.isEmpty(season)){
            wrapper.eq("shoesSeason",season);
        }
        if(!StringUtils.isEmpty(type)){
            wrapper.eq("shoesType",type);
        }
        if(!StringUtils.isEmpty(warehouseNumber)){
            wrapper.eq("warehouseNumber",warehouseNumber);
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
        shoesService.page(pageShoes,wrapper);
        long total = pageShoes.getTotal(); // 总记录数
        List<Shoes> records = pageShoes.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
    // 添加鞋子接口的方法
    @ApiOperation(value = "添加鞋子")
    @PostMapping("/addShoes")
    public R addShoes(@RequestBody Shoes shoes){
        boolean save = shoesService.save(shoes);
        if(save)
            return R.ok();
        return R.error();
    }
    // 根据鞋子ID进行查询
    @ApiOperation(value = "鞋子ID查询")
    @GetMapping("/getShoes/{id}")
    public R getShoes(@PathVariable String id){
        Shoes shoes = shoesService.getById(id);
        return R.ok().data("shoes",shoes);
    }
    // 鞋子信息修改功能
    @ApiOperation(value = "鞋子信息修改")
    @PostMapping("/updateShoes")
    public R updateShoes(@RequestBody Shoes shoes){
        boolean flag = shoesService.updateById(shoes);
        if(flag)
            return R.ok();
        return R.error();
    }
}

