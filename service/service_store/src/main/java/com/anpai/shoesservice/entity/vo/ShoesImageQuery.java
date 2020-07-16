package com.anpai.shoesservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/8 18:17
 */

@Data
public class ShoesImageQuery {
    @ApiModelProperty(value = "鞋子编号", example = "2017001001" )
    private Integer shoesNumber;

    @ApiModelProperty(value = "查询开始时间", example = "2020-05-09 16:41:36")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2020-06-07 00:00:00")
    private String end;
}
