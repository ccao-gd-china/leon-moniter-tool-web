package com.godaddy.ecomm.dao.fulfillment;

import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ErrorOrderRequestXmlCustomMapper extends Mapper {

  List<String> getAllPrivateLabelId(@Param("snapshot_id") int snapshot_id) throws Exception;

  List<ErrorOrderRequestXml> getErrorOrderRequests(@Param("snapshot_id") int snapshot_id,
                                                   @Param("startDate") String startDate,
                                                   @Param("endDate") String endDate,
                                                   @Param("privateLabelId") int privateLabelId,
                                                   @Param("startIndex") int startIndex,
                                                   @Param("amount") int amount,
                                                   @Param("filterMessage") String filterMessage,
                                                   @Param("filterPLID") String filterPLID,
                                                   @Param("filterSeverity") String filterSeverity,
                                                   @Param("sortByMessage") int sortByMessage,
                                                   @Param("sortByPLID") int sortByPLID,
                                                   @Param("sortBySeverity") int sortBySeverity) throws Exception;

  List<ErrorOrderRequestXml> getLatestErrorOrderRequests(@Param("startIndex") int startIndex,
                                                         @Param("amount") int amount) throws Exception;

  int getErrorOrderRequestsNum(@Param("snapshot_id") int snapshot_id,
                               @Param("startDate") String startDate,
                               @Param("endDate") String endDate,
                               @Param("privateLabelId") int privateLabelId,
                               @Param("filterMessage") String filterMessage,
                               @Param("filterPLID") String filterPLID,
                               @Param("filterSeverity") String filterSeverity) throws Exception;

  int getLatestSnapshot_id() throws Exception;

}
