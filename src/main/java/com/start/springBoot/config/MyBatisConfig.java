package com.start.springBoot.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.start.springBoot"
        ,annotationClass = org.springframework.stereotype.Repository.class)
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:config/config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/testMapper.xml"));
        return bean.getObject();
    }

    @Bean
    public SqlSession sqlSession(SqlSessionFactory sessionFactory){
        SqlSessionTemplate template = new SqlSessionTemplate(sessionFactory);
        return template;
    }
}
