package com.demo.jpa.po;

import com.demo.jpa.vo.TestDemoAndDetailVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "test_demo")
@SqlResultSetMapping(
        name = "test",
        classes = {
                @ConstructorResult(
                        targetClass = TestDemoAndDetailVo.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "userName", type = String.class),
                                @ColumnResult(name = "userAge", type = Integer.class),
                                @ColumnResult(name = "descTodo", type = String.class),
                                @ColumnResult(name = "updateTime", type = String.class),
                                @ColumnResult(name = "createTime", type = String.class),
                                @ColumnResult(name = "extendInfo", type = String.class)
                        }
                )
        }
)
public class TestDemo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private Integer userAge;

    @Column
    private String descTodo;

    @UpdateTimestamp
    private Timestamp updateTime;

    @Column
    private Timestamp createTime;

    @JsonIgnore
    @OneToMany(mappedBy = "testDemo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<TestDemoDetail> demoDetails;
}
