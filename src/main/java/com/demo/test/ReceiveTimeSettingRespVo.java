package com.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveTimeSettingRespVo implements Serializable {

    private static final long serialVersionUID = -7022942517500906890L;
//    @ApiModelProperty("是否开启不接单的特殊时间段0不开启 1开启")
    private Integer isEnable = 0;

//    @ApiModelProperty("店铺code")
    private String depotCode;

//    @ApiModelProperty("详细设置时间段")
    private List<ReceiveTimeSettingDetailVo> details;

    public ReceiveTimeSettingRespVo(Integer isEnable, String depotCode) {
        this.isEnable = isEnable;
        this.depotCode = depotCode;
    }
}