package com.demo.item.test;


import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class OrderListExcelRespDto implements Serializable {

    private String activityTitle = "测试活动标题";

    private String activityTime = "2020.01.01 - 2021.01.10";

    private String activityAdress = "太平洋保险景荣大厦";

    private String priceType = "主题1/2132";

    private String company = "商家信息 18111229182";

    private List<Object[]> orderLists;


    @Data
    public static class OrderList {

        private int id;

        private String userName;

        private String userMobile;

        private String total;

        private String orderCode;

        private String insOrderNo;

        private String isNeedSignOut;

        private String company;

        private String todo;

    }


    public void genData() {
        List<Object[]> lists = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Object[] o = new Object[]{i + 1,"商家" + i,"保单" + i,"不需要","我是备注","我是备注","订单号码","手机号码"};
            lists.add(o);
        }
        this.setOrderLists(lists);
    }


}