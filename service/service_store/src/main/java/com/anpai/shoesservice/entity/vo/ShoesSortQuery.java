package com.anpai.shoesservice.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/8 22:38
 */

@Data
public class ShoesSortQuery {
    @ApiModelProperty(value = "鞋子编号", example = "2017001001" )
    private Integer shoesNumber;

    @ApiModelProperty(value = "码数", example = "37" )
    private Float shoesSize;

    @ApiModelProperty(value = "颜色",  example = "黄色" )
    @TableField("shoesColor")
    private String shoesColor;

    @ApiModelProperty(value = "查询开始时间", example = "2020-05-09 16:41:36")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2020-06-07 00:00:00")
    private String end;

}
