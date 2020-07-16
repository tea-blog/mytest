package com.anpai.shoesservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/8 23:00
 */
@Data
public class ShoesWarehouseQuery {
    @ApiModelProperty(value = "鞋子编号")
    @TableField("shoesNumber")
    private Integer shoesNumber;

    @ApiModelProperty(value = "仓库号（暂定四个仓库）")
    @TableField("warehouseNumber")
    private Integer warehouseNumber;

    @ApiModelProperty(value = "鞋厂名")
    @TableField("shoesFactoryName")
    private String shoesFactoryName;

    @ApiModelProperty(value = "鞋子单价")
    @TableField("shoesUnitPrice")
    private BigDecimal shoesUnitPrice;

    @ApiModelProperty(value = "查询开始时间", example = "2020-05-09 16:41:36")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2020-06-07 00:00:00")
    private String end;
}
