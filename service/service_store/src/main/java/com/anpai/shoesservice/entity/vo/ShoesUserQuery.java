package com.anpai.shoesservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/13 15:48
 */

@Data
public class ShoesUserQuery {
    @ApiModelProperty(value = "鞋子名字（面向用户）", example = "小白鞋")
    private String shoesUserName;

    @ApiModelProperty(value = "鞋子店铺（面向用户）", example = "回力")
    private String shoesUserStore;

}
