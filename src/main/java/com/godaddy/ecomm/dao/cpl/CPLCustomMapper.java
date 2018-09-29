package com.godaddy.ecomm.dao.cpl;

import com.godaddy.ecomm.base.cpl.AutoRefund;
import com.godaddy.ecomm.base.cpl.CPLGatewaySelectionFailureForAutorenewal;
import com.godaddy.ecomm.base.cpl.CPLISCRefundLog;
import com.godaddy.ecomm.base.cpl.CommonPurchaseLogWithSomeKeyFields;
import com.godaddy.ecomm.base.cpl.CommonResponseReason;
import com.godaddy.ecomm.base.cpl.ErrorPurchaseLog;
import com.godaddy.ecomm.dao.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CPLCustomMapper extends Mapper {

  List<ErrorPurchaseLog> getErrorPurchaseLogs(@Param("snapshot_id") int snapshot_id,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  String get_exception_monitor_str() throws Exception;

  List<CPLGatewaySelectionFailureForAutorenewal> getALLCPLAutorenewalLogsByPeriod(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> getGatewayOutageDatas(
    @Param("startIndex") int startIndex,
    @Param("amount") int amount,
    @Param("which_column") String which_column,
    @Param("sort_way") String sort_way) throws Exception;

  List<CPLGatewaySelectionFailureForAutorenewal> get_cpl_gateway_selection_failure_details_logs(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount,
    @Param("order_id") String order_id,
    @Param("shopper_id") String shopper_id) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> get_all_cpl_exception_monitor_logs_by_period(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount,
    @Param("response_code") int response_code,
    @Param("reason_code") int reason_code) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> get_EComm_error_logs_by_period(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<AutoRefund> get_cpl_autorefund_logs_by_period(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> get_cpl_exception_logs_by_sort(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount,
    @Param("response_code") int response_code,
    @Param("reason_code") int reason_code,
    @Param("which_column") String which_column,
    @Param("sort_way") String sort_way) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> get_EComm_error_logs_by_sort(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount,
    @Param("which_column") String which_column,
    @Param("sort_way") String sort_way) throws Exception;

  String get_error_description(
    @Param("response_code") int response_code,
    @Param("reason_code") int reason_code);

  List<CPLISCRefundLog> getCPLISCRefundLogsByPeriod(@Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<ErrorPurchaseLog> getErrorPurchaseLogsByPeriod(@Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<CPLGatewaySelectionFailureForAutorenewal> getCPLAutorenewalByLatestSnapshotId(
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<CPLISCRefundLog> getCPLISCRefundLogsByLatestSnapshotId(
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<ErrorPurchaseLog> getErrorPurchaseLogsByLatestSnapshotId(
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<CPLISCRefundLog> getALLCPLISCRefundLogs(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  List<ErrorPurchaseLog> getAllErrorPurchaseLogs(@Param("startIndex") int startIndex,
    @Param("amount") int amount) throws Exception;

  int getCount(@Param("dbName") String dbName,
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  int get_cpl_gateway_selection_failure_details_count(@Param("dbName") String dbName,
    @Param("startDate") String startDate,
    @Param("endDate") String endDate,
    @Param("order_id") String order_id,
    @Param("shopper_id") String shopper_id) throws Exception;

  int getIndexCount(@Param("dbName") String dbName) throws Exception;

  String get_data_from_db_usefulDate(@Param("which_field") String which_field) throws Exception;

  String getTime(@Param("dbName") String dbName,
    @Param("up_down") String up_down) throws Exception;

  String getLastUpdateTime(@Param("fieldName") String dbName) throws Exception;

  List<AutoRefund> get_auto_refund_data_with_time_limit_from_local_db(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  List<CommonPurchaseLogWithSomeKeyFields> get_all_cpl_data_with_time_limit_from_local_db(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  List<CommonResponseReason> get_all_common_response_reason_list() throws Exception;

  int getCountByPeriod(@Param("dbName") String dbName,
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  int get_cpl_exception_monitor_count_by_period(
    @Param("response_code") int response_code,
    @Param("reason_code") int reason_code,
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  int get_EComm_error_count_by_period(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  int get_cpl_autorefund_logs_count_by_period(
    @Param("startDate") String startDate,
    @Param("endDate") String endDate) throws Exception;

  int getCPLISCRefundLogCountByLatestSnapshotId() throws Exception;

  int getCPLAutorenewalCountByLatestSnapshotId() throws Exception;

  int getErrorPurchaseCountByLatestSnapshotId() throws Exception;

}
