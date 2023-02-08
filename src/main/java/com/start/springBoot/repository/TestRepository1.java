package com.start.springBoot.repository;

import com.start.springBoot.dto.TestDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository1 {
    @Autowired
    SqlSessionTemplate sessionTemplate;

    public List<TestDTO> findAll(){
        return sessionTemplate.selectList("test.findAll");
    }

}
