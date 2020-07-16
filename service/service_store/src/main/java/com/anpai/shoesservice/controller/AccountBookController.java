package com.anpai.shoesservice.controller;


import com.anpai.commomutils.R;
import com.anpai.shoesservice.entity.AccountBook;
import com.anpai.shoesservice.entity.vo.AccountBookQuery;
import com.anpai.shoesservice.service.AccountBookService;
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
@Api(tags = "账本管理")
@RestController
@RequestMapping("/shoesservice/account-book")
public class AccountBookController {
    @Autowired
    AccountBookService accountBookService;

    @ApiOperation(value = "所有账本列表")
    @GetMapping("/findAll")
    public R findAllAccountBook(){
        List<AccountBook> list = accountBookService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除显示")
    @DeleteMapping("{id}")    // id值通过路劲传id值
    public R removeAccountBook(@ApiParam(name = "id", value = "账本id", required = true) @PathVariable Long id){
        boolean flag = accountBookService.removeById(id);
        if(flag){
            return R.ok();
        }
        else{
            return R.error();
        }
    }

    // 条件查询再分页的方法
    @ApiOperation(value = "条件查询再分页")
    @PostMapping("/pageAccountBookCondition/{current}/{limit}")
    public R pageAccountBookCondition(@PathVariable Long current,
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) AccountBookQuery accountBookQuery){

        // 常见page对象
        Page<AccountBook> accountBookPage = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<AccountBook> wrapper = new QueryWrapper<>();

        // 多条件组合查询
        // mybatis 学过动态sql
        String description = accountBookQuery.getDescription();
        Integer quantity = accountBookQuery.getQuantity();
        BigDecimal totalAmount = accountBookQuery.getTotalAmount();
        BigDecimal unitPrice = accountBookQuery.getUnitPrice();
        String begin = accountBookQuery.getBegin();
        String end = accountBookQuery.getEnd();


        // 判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(description)){
            // 构造条件
            wrapper.like("description",description);
        }
        if(!StringUtils.isEmpty(quantity)){
            wrapper.eq("quantity",quantity);
        }
        if(!StringUtils.isEmpty(totalAmount)){
            wrapper.eq("totalAmount",totalAmount);
        }
        if(!StringUtils.isEmpty(unitPrice)){
            wrapper.eq("unitPrice",unitPrice);
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
        accountBookService.page(accountBookPage,wrapper);
        long total = accountBookPage.getTotal(); // 总记录数
        List<AccountBook> records = accountBookPage.getRecords(); // 数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 添加账本接口的方法
    @ApiOperation(value = "添加账本")
    @PostMapping("/addAccountBook")
    public R addAccountBook(@RequestBody AccountBook accountBook){
        boolean save = accountBookService.save(accountBook);
        if(save)
            return R.ok();
        return R.error();
    }

    // 根据账本ID进行查询
    @ApiOperation(value = "账本ID查询")
    @GetMapping("/getAccountBook/{id}")
    public R getAccountBook(@PathVariable String id){
        AccountBook accountBook = accountBookService.getById(id);
        return R.ok().data("accountBook",accountBook);
    }

    // 账本信息修改功能
    @ApiOperation(value = "账本信息修改")
    @PostMapping("/updateAccountBook")
    public R updateAccountBook(@RequestBody AccountBook accountBook){
        boolean flag = accountBookService.updateById(accountBook);
        if(flag)
            return R.ok();
        return R.error();
    }


}

