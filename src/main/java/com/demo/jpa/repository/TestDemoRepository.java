package com.demo.jpa.repository;

import com.demo.jpa.po.TestDemo;
import com.demo.jpa.vo.TestDemoAndDetailVo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.boot.model.convert.spi.JpaAttributeConverterCreationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.SqlResultSetMappings;
import java.util.List;

@Repository
public interface TestDemoRepository extends JpaRepository<TestDemo,Long>, JpaSpecificationExecutor<TestDemo> {

    @Override
    List<TestDemo> findAll();
}
