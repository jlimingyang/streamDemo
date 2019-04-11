package com.item.beans;

import com.item.enums.CmsJumpType;
import lombok.Data;

import java.io.Serializable;

@Data
public class Test01 implements Serializable {

    private Integer id;
    private String name;
    private CmsJumpType cmsJumpType;
}
