package com.godaddy.ecomm.utils;

import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MapperFactory {

  private static Logger logger = LoggerFactory.getLogger(MapperFactory.class);

  public static <T> T createMapper(Class<? extends Mapper> clazz, DataSourceEnvironment environment) throws IOException {
    SqlSessionFactory sqlSessionFactory = DataSourceSqlSessionFactory.getSqlSessionFactory(environment);
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    Mapper mapper = sqlSession.getMapper(clazz);

    return (T) MapperProxy.bind(mapper, sqlSession);

  }
}
