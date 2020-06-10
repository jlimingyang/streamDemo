package com.demo.item.easyExcelProject;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DemoDataListener extends AnalysisEventListener<Map<Object, Object>> {

    private Set<String> allList;

    Map<String, Map<String, Double>> allMap;
    private Boolean a;

    public DemoDataListener() {

    }

    public DemoDataListener(Set<String> allList) {
        this.allList = allList;
    }

    public DemoDataListener(Boolean a, Map<String, Map<String, Double>> allMap) {
        this.allMap = allMap;
        this.a = a;
    }

    @Override
    public void invoke(Map<Object, Object> objectObjectMap, AnalysisContext analysisContext) {
        if (Objects.equals(a, true)) {
            if (objectObjectMap.get(22) != null && objectObjectMap.get(21) != null && String.valueOf(objectObjectMap.get(21)).equals("SUCCESS")) {
                String orderName = String.valueOf(objectObjectMap.get(22));
                String orderCode = orderName.substring(orderName.indexOf("20"), orderName.length());
                Double fee = Double.valueOf(String.valueOf(objectObjectMap.get(2)));
                Map<String, Double> feeMap = Maps.newHashMap();
                if (allMap.containsKey(orderCode)) {
                    final Map<String, Double> stringDoubleMap = allMap.get(orderCode);
                    stringDoubleMap.put(orderName + "--"+(stringDoubleMap.size()+1), fee);
                } else {
                    feeMap.put(orderName +"--1", fee);
                    allMap.put(orderCode, feeMap);
                }
            }
        } else {
            allList.add(String.valueOf(objectObjectMap.get(1)));
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}