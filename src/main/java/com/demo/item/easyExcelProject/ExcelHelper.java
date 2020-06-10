package com.demo.item.easyExcelProject;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelHelper {

    @Test
    public static void main(String[] args) {
        List<DemoClass> resList = Lists.newArrayList();
        Set<String> allList = Sets.newHashSet();
        Map<String, Map<String, Double>> allMap = Maps.newHashMap();
        EasyExcel.read(new File("/Users/limingyang/Desktop/5月微店业绩(1).xlsx"), new DemoDataListener(allList)).sheet().doRead();
        EasyExcel.read(new File("/Users/limingyang/Desktop/微好店银行收款明细.xlsx"), new DemoDataListener(true, allMap)).sheet().doRead();
//        System.out.println(JSON.toJSONString(allMap));
        final Set<String> strings = allMap.keySet();
        strings.forEach(s -> {
            if (allList.contains(s)) {
                final Map<String, Double> stringDoubleMap = allMap.get(s);
                final Set<String> strings1 = stringDoubleMap.keySet();
                strings1.forEach(a -> {
                    System.out.println("订单号:" + s + ",商品名:" + a + ",金额:" + stringDoubleMap.get(a));
                    DemoClass demoClass = new DemoClass();
                    demoClass.setAmount(stringDoubleMap.get(a));
                    demoClass.setName(a);
                    demoClass.setOrderCode(s);
                    resList.add(demoClass);
                });
            }
        });

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(new File("/Users/limingyang/Desktop/结果表.xlsx"),DemoClass.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("结果").build();
            excelWriter.write(resList, writeSheet);
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }



}
