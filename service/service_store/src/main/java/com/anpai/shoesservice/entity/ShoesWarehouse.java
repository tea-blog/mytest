package com.anpai.shoesservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author testjava
 * @since 2020-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShoesWarehouse对象", description="")
public class ShoesWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鞋库Id")
    @TableId(value = "warehouseId", type = IdType.AUTO)
    private Long warehouseId;

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

    @ApiModelProperty(value = "鞋子总数量")
    @TableField("shoesSumQuantity")
    private Double shoesSumQuantity;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更改时间")
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "是否有效")
    @TableField("isValid")
    @TableLogic
    private Integer isValid;


}
