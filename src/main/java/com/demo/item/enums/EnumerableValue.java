package com.demo.item.enums;


import java.io.Serializable;

/**
 * 枚举int的上层接口,只有枚举才应该继承本接口
 * 配合BaseEnumValueConverter使用
 *
 * @author yanweijin 2016年5月5日
 */
public interface EnumerableValue extends Serializable
{

    int getValue();

}
