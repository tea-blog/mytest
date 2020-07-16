package com.anpai.shoesservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/8 22:51
 */
@Data

public class AccountBookQuery {

    @ApiModelProperty(value = "说明")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "单价")
    @TableField("unitPrice")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "总金额")
    @TableField("totalAmount")
    private BigDecimal totalAmount;


    @ApiModelProperty(value = "查询开始时间", example = "2020-05-09 16:41:36")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2020-06-07 00:00:00")
    private String end;
}
