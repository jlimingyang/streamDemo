package com.demo.test;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReceiveTimeSettingVo implements Serializable {

    private static final long serialVersionUID = 363949299417459603L;

    private Integer isEnable = 0;

    private String depotCode;

    private List<ReceiveTimeSettingDetailVo> details;

}