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
@ApiModel(value="AccountBook对象", description="")
public class AccountBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账本id")
    @TableId(value = "accountBookId", type = IdType.AUTO)
    private Long accountBookId;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "单价")
    @TableField("unitPrice")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "总金额")
    @TableField("totalAmount")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "createTime", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更改时间")
    @TableField(value = "updateTime", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "1：有效  0：无效")
    @TableField("isValid")
    @TableLogic
    private Integer isValid;


}
