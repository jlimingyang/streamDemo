package com.spring.strategy;

import com.google.common.collect.Maps;
import com.spring.log.PrintLog;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class SaleService {

 Map<String,DiscountStategy> map = Maps.newHashMap();

    public SaleService(List<DiscountStategy> stategies) {
       this.map = stategies.stream().collect(Collectors.toMap(DiscountStategy::type, a->a));
    }

    @PrintLog
    public double sale(String type,double fee){
        return map.get(type).discount(fee);
    }
}
