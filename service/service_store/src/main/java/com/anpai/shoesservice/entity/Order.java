package com.anpai.shoesservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author xsz
 * @since 2020-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "鞋子编号")
    @TableField("shoesNumber")
    private Integer shoesNumber;

    @ApiModelProperty(value = "用户ID")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "订单编号")
    @TableField("oderNumber")
    private Integer oderNumber;

    @ApiModelProperty(value = "订单状态 0 未签收（默认） 1 已签收")
    @TableField("orderStatus")
    private Boolean orderStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "更改时间")
    @TableField("updateTime")
    private Date updateTime;

    @ApiModelProperty(value = "是否有效")
    @TableField("isValid")
    private Integer isValid;


}
