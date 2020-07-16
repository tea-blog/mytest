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
@ApiModel(value="ShoesSort对象", description="")
public class ShoesSort implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鞋子分类ID")
    @TableId(value = "shoesSortId", type = IdType.AUTO)
    private Long shoesSortId;

    @ApiModelProperty(value = "鞋子编号（一共十位，前四位是生产年份，五到七位是生产厂家ID，最后三位是鞋子的款式类型编号）")
    @TableField("shoesNumber")
    private Long shoesNumber;

    @ApiModelProperty(value = "码数")
    @TableField("shoesSize")
    private Float shoesSize;

    @ApiModelProperty(value = "颜色")
    @TableField("shoesColor")
    private String shoesColor;

    @ApiModelProperty(value = "鞋子数量")
    @TableField("shoesQuantity")
    private Long shoesQuantity;

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
