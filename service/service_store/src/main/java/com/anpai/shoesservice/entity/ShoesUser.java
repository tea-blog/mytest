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
 * @author xsz
 * @since 2020-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShoesUser对象", description="")
public class ShoesUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "鞋子ID（面向用户）")
    @TableId(value = "shoesUserId", type = IdType.ID_WORKER)
    private Integer shoesUserId;

    @ApiModelProperty(value = "鞋子名字（面向用户）")
    @TableField("shoesUserName")
    private String shoesUserName;

    @ApiModelProperty(value = "鞋子图片地址（面向用户）")
    @TableField("shoesUserImageUrl")
    private String shoesUserImageUrl;

    @ApiModelProperty(value = "鞋子单价（面向用户）")
    @TableField("shoesUserPrice")
    private String shoesUserPrice;

    @ApiModelProperty(value = "鞋子编码")
    @TableField("shoesNumber")
    private String shoesNumber;

    @ApiModelProperty(value = "鞋子评论数（面向用户）")
    @TableField("shoesUserComment")
    private String shoesUserComment;

    @ApiModelProperty(value = "鞋子店铺（面向用户）")
    @TableField("shoesUserStore")
    private String shoesUserStore;

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
