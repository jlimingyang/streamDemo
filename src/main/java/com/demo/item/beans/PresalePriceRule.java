package com.demo.item.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PresalePriceRule {

    /**
     * 阶梯人数
     */
    private Integer ladderPeople;

    /**
     * 阶梯价格
     */
    private Integer ladderPrice;

}
