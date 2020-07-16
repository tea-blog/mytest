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
@ApiModel(value="ShoesImage对象", description="")
public class ShoesImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鞋子图片Id")
    @TableId(value = "shoesImageId", type = IdType.AUTO)
    private Long shoesImageId;

    @ApiModelProperty(value = "鞋子编号")
    @TableField("shoesNumber")
    private Long shoesNumber;

    @ApiModelProperty(value = "图片地址")
    @TableField("imageUrl")
    private String imageUrl;

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
