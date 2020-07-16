package com.anpai.shoesservice.entity;

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
@ApiModel(value="Shoes对象", description="")
public class Shoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鞋子ID")
    @TableId(value = "shoesId", type = IdType.AUTO)
    private Long shoesId;

    @ApiModelProperty(value = "仓库号")
    @TableField("warehouseNumber")
    private Integer warehouseNumber;

    @ApiModelProperty(value = "鞋子编号（一共十位，前四位是生产年份，五到七位是生产厂家ID，最后三位是鞋子的款式类型编号）")
    @TableField("shoesNumber")
    private Long shoesNumber;

    @ApiModelProperty(value = "鞋名")
    @TableField("shoesName")
    private String shoesName;

    @ApiModelProperty(value = "适合季节（0：四季 1：春 2：夏 3：秋 4：冬）")
    @TableField("shoesSeason")
    private Integer shoesSeason;

    @ApiModelProperty(value = "类型款式（1：男款 2：女款 3:儿童款）")
    @TableField("shoesType")
    private Integer shoesType;

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
