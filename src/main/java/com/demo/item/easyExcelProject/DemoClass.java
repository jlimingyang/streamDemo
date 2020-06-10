package com.demo.item.easyExcelProject;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoClass {

    @ExcelProperty(value = "订单号", index = 0)
    private String orderCode;

    @ExcelProperty(value = "商品名称", index = 1)
    private String name;

    @ExcelProperty(value = "退款金额", index = 2)
    private Double amount;
}
