package com.godaddy.ecomm.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DataSourceSqlSessionFactory {

  private static Logger logger = LoggerFactory.getLogger(DataSourceSqlSessionFactory.class);
  private static final String CONFIGURATION_PATH = "mybatis-config.xml";
  private static final Map<DataSourceEnvironment, SqlSessionFactory> sqlSessionFactories = new HashMap<>();

  /**
   * According to the corresponding access to the specified DataSourceEnvironment
   * SqlSessionFactory.
   */
  public static SqlSessionFactory getSqlSessionFactory(DataSourceEnvironment environment)
      throws IOException {
    SqlSessionFactory sqlSessionFactory = sqlSessionFactories.get(environment);

    if (sqlSessionFactory != null) {
      return sqlSessionFactory;
    } else {
      InputStream inputStream = null;
      try {
        inputStream = Resources.getResourceAsStream(CONFIGURATION_PATH);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.name());
        logger.info("Get {} SqlSessionFactory successfully.", environment.name());
      } catch (IOException e) {
        logger.warn("Get {} SqlSessionFactory error.", environment.name());
        logger.error(e.getMessage(), e);
      } finally {
        if (inputStream != null) {
          inputStream.close();
        }
      }
    }

    sqlSessionFactories.put(environment, sqlSessionFactory);
    return sqlSessionFactory;
  }

}
