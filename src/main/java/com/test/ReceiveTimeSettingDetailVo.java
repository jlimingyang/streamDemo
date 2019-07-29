package com.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveTimeSettingDetailVo implements Serializable {
    private static final long serialVersionUID = 4185818489122582518L;

//    @ApiModelProperty("1上午接单时间 2下午接单时间 3晚上接单时间 4特殊时间段 5营业时间 6上午到下午接单高峰时间 7下午到晚上接单高峰时间")
//    private Integer settingType;

//    @ApiModelProperty("接单时间开始时间")
    private String beginTime;

//    @ApiModelProperty("接单时间结束时间")
    private String endTime;

//    @ApiModelProperty("特殊时间段文字描述")
    private String text;
}