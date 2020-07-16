package com.anpai.shoesservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/** 
 * @author xiesongzhi
 * @Description:
 * @create: 2020/6/7 2:59
 */
@Data
public class ShoesQuery {
    @ApiModelProperty(value = "鞋名,模糊查询", example = "拖鞋")
    private String name;

    @ApiModelProperty(value = "仓库号", example = "1" )
    private Integer warehouseNumber;

    @ApiModelProperty(value = "适合季节 0：四季 1：春 2：夏 3：秋 4：冬", example = "1")
    private Integer season;

    @ApiModelProperty(value = "类型款式 1：男款 2：女款 3：儿童款 ", example="1" )
    private Integer type;

    @ApiModelProperty(value = "查询开始时间", example = "2020-05-09 16:41:36")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2020-06-07 00:00:00")
    private String end;

}
