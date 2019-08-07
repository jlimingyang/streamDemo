package com.demo.item.beans;

import com.demo.item.enums.CmsJumpType;
import lombok.Data;

import java.io.Serializable;

@Data
public class Test01 implements Serializable {

    private Integer id;
    private String name;
    private CmsJumpType cmsJumpType;
}
