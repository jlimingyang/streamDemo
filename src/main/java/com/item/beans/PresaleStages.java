package com.item.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PresaleStages {

        private boolean currentStage = false;

        private Integer ladderPeople;

        private Integer ladderPrice;
}
