package com.demo.jpa.repository;

import com.demo.jpa.po.TestDemo;
import org.hibernate.boot.model.convert.spi.JpaAttributeConverterCreationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDemoRepository extends JpaRepository<TestDemo,Long> {
}
