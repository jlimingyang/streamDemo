package com.demo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDemoAndDetailVo {

    @Id
    private Long id;

    private String userName;

    private Integer userAge;

    private String descTodo;

    private String updateTime;

    private String createTime;

    private String extendInfo;
}
