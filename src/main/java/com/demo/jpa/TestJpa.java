package com.demo.jpa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.SpringBootBaseTest;
import com.demo.jpa.po.TestDemo;
import com.demo.jpa.repository.TestDemoRepository;
import com.demo.jpa.vo.TestDemoAndDetailVo;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.connection.SortParameters;

import javax.persistence.EntityManager;
import javax.persistence.JoinTable;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


public class TestJpa extends SpringBootBaseTest {

    @Autowired
    private TestDemoRepository testDemoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testjpa01() {
        List<TestDemo> all = testDemoRepository.findAll();
        System.out.println(JSON.toJSONString(all));
    }

    @Test
    public void testjpa02() {
        String query = "SELECT a.id AS id,DATE_FORMAT(a.create_time,\"%Y-%m-%d\") AS createTime,a.desc_todo AS descTodo,DATE_FORMAT(a.update_time,\"%Y-%m-%d\") AS updateTime,a.user_age AS userAge,a.user_name AS userName,b.extend_info AS extendInfo FROM test_demo a LEFT JOIN test_demo_detail b ON a.id = b.test_id";
        Query nativeQuery = entityManager.createNativeQuery(query, "test");
        System.out.println(JSON.toJSONString(nativeQuery.getResultList()));
    }

    @Test
    public void testjpa03(){
        Page<TestDemo> testDemos = testDemoRepository.findAll((Specification<TestDemo>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> p = Lists.newArrayList();
            p.add(criteriaBuilder.equal(root.get("id").as(Long.class),1));
            p.add(criteriaBuilder.not(root.get("id").in(10,12)));
//            p.add(criteriaBuilder.equal(root.join("omsOrderDetails", JoinType.LEFT).get("twoLevelId").as(Long.class), queryVo.getCategoryId()));
            return criteriaQuery.where(p.toArray(new Predicate[p.size()])).orderBy(criteriaBuilder.desc(root.get("id"))).getRestriction();
        },new PageRequest(0,10));
        System.out.println(JSON.toJSONString(testDemos.getContent()));
    }

}
