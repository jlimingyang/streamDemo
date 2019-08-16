package com.demo.jpa.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.jpa.vo.TestDemoAndDetailVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "test_demo_detail")
public class TestDemoDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String extendInfo;

    //orphanRemoval = true，那么这个操作会删除address对象，如果为false，则会删除他们的关系，将address对user的引用设置为null。
    @JsonIgnore
    @JSONField()
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestDemo testDemo;
}
