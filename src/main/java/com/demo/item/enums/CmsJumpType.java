package com.demo.item.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  CmsJumpType implements EnumerableValue {
    NOTHING(0,"无"),
    PRODUCT_CATEGORY(1,"商品分类"),
    PRODUCT_DETAIL(2,"商品详情"),
    LINK(3,"外部链接");

    private int value;
    private String desc;
}
