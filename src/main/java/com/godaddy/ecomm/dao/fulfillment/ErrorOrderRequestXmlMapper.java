package com.godaddy.ecomm.dao.fulfillment;

import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXmlExample;
import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ErrorOrderRequestXmlMapper extends Mapper {
    int countByExample(ErrorOrderRequestXmlExample example);

    int deleteByExample(ErrorOrderRequestXmlExample example);

    int deleteByPrimaryKey(Integer errorId);

    int insert(ErrorOrderRequestXml record);

    int insertSelective(ErrorOrderRequestXml record);

    List<ErrorOrderRequestXml> selectByExampleWithBLOBs(ErrorOrderRequestXmlExample example);

    List<ErrorOrderRequestXml> selectByExample(ErrorOrderRequestXmlExample example);

    ErrorOrderRequestXml selectByPrimaryKey(Integer errorId);

    int updateByExampleSelective(@Param("record") ErrorOrderRequestXml record, @Param("example") ErrorOrderRequestXmlExample example);

    int updateByExampleWithBLOBs(@Param("record") ErrorOrderRequestXml record, @Param("example") ErrorOrderRequestXmlExample example);

    int updateByExample(@Param("record") ErrorOrderRequestXml record, @Param("example") ErrorOrderRequestXmlExample example);

    int updateByPrimaryKeySelective(ErrorOrderRequestXml record);

    int updateByPrimaryKeyWithBLOBs(ErrorOrderRequestXml record);

    int updateByPrimaryKey(ErrorOrderRequestXml record);
}