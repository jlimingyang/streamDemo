package com.demo.item.serilizer;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class Ser implements Serializable {


    private static final long serialVersionUID = 3781908750187901303L;

    private String name;

    private Integer age;

    private List<SerDemo> demos = Lists.newArrayList();


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SerDemo implements Serializable {
        private String demoName;

        private Integer demoAge;
    }

}
