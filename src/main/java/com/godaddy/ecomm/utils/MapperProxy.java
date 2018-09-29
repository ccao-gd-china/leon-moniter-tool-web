package com.godaddy.ecomm.utils;

import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MapperProxy implements InvocationHandler {

  private Mapper mapper;
  private SqlSession sqlSession;

  public MapperProxy(Mapper mapper, SqlSession sqlSession) {
    this.mapper = mapper;
    this.sqlSession = sqlSession;
  }

  public static Mapper bind(Mapper mapper, SqlSession sqlSession) {
    return (Mapper) Proxy.newProxyInstance(
        mapper.getClass().getClassLoader(),
        mapper.getClass().getInterfaces(),
        new MapperProxy(mapper, sqlSession));
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Object object = null;
    try {
      object = method.invoke(mapper, args);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      sqlSession.close();
    }

    return object;
  }
}
