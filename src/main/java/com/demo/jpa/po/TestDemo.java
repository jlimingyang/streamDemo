package com.demo.jpa.po;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "test_demo")
public class TestDemo implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private int userAge;

    @Column
    private String descTodo;

    @UpdateTimestamp
    private Timestamp updateTime;
}
