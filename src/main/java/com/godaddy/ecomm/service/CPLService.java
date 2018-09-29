package com.godaddy.ecomm.service;

import com.godaddy.ecomm.base.cpl.AutoRefund;
import com.godaddy.ecomm.base.cpl.CPLGatewaySelectionFailureForAutorenewal;
import com.godaddy.ecomm.base.cpl.CPLISCRefundLog;
import com.godaddy.ecomm.base.cpl.CPLQueryVo;
import com.godaddy.ecomm.base.cpl.CommonPurchaseLogWithSomeKeyFields;
import com.godaddy.ecomm.base.cpl.CommonResponseReason;
import com.godaddy.ecomm.base.cpl.ErrorPurchaseLog;
import com.godaddy.ecomm.dao.cpl.CPLCustomMapper;
import com.godaddy.ecomm.utils.Common;
import com.godaddy.ecomm.utils.DataSourceEnvironment;
import com.godaddy.ecomm.utils.MapperFactory;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class CPLService {

  private Logger logger = LoggerFactory.getLogger(CPLService.class);

  private CPLCustomMapper getCustomMapper() throws IOException {
    return MapperFactory.
      createMapper(CPLCustomMapper.class, DataSourceEnvironment.Local);
  }

  /**
   * Method to execute request from Slack.
   */
  public List<ErrorPurchaseLog> getErrorPurchaseLogs(CPLQueryVo cplQueryVo) throws Exception {

    return getCustomMapper()
      .getErrorPurchaseLogs(cplQueryVo.getSnapshot_id(), cplQueryVo.getStartIndex(),
        cplQueryVo.getAmount());
  }

  public int getIndexCount(String dbName) throws Exception {
    return getCustomMapper().getIndexCount(dbName);
  }

  public String get_exception_monitor_str() throws Exception {
    return getCustomMapper().get_exception_monitor_str();
  }

  public String get_data_from_db_usefulDate(String which_field) throws Exception {
    return getCustomMapper().get_data_from_db_usefulDate(which_field);
  }

  public String get_auto_refund_str(String lastUpdateTime) throws Exception {

    String time_of_cpl_auto_refund = lastUpdateTime;
    //split to 3 part,ever part is 5 mins
    Date time_0_min_ago_date = Common.formatToDate(time_of_cpl_auto_refund);
    Date time_5_min_ago_date = Common.add_minutes(time_0_min_ago_date, -5);
    Date time_10_min_ago_date = Common.add_minutes(time_0_min_ago_date, -10);
    Date time_15_min_ago_date = Common.add_minutes(time_0_min_ago_date, -15);

    String time_15_min_ago_str = Common.formatDateToString(time_15_min_ago_date).substring(0, 19);
    String time_10_min_ago_str = Common.formatDateToString(time_10_min_ago_date).substring(0, 19);
    String time_5_min_ago_str = Common.formatDateToString(time_5_min_ago_date).substring(0, 19);
    String time_0_min_ago_str = Common.formatDateToString(time_0_min_ago_date).substring(0, 19);

    List<AutoRefund> autoRefundList = getCustomMapper()
      .get_auto_refund_data_with_time_limit_from_local_db(time_15_min_ago_str, time_0_min_ago_str);
    List<AutoRefund> auto_refund_rows_10_mins_ago_list = new ArrayList<>();
    List<AutoRefund> auto_refund_rows_5_mins_ago_list = new ArrayList<>();
    List<AutoRefund> auto_refund_rows_0_mins_ago_list = new ArrayList<>();

    for (int i = 0; i < autoRefundList.size(); i++) {
      Date date_judge_time = Common.formatToDate(autoRefundList.get(i).getPrior_time());
      if (date_judge_time.before(time_10_min_ago_date)) {
        auto_refund_rows_10_mins_ago_list.add(autoRefundList.get(i));
      } else if (date_judge_time.before(time_5_min_ago_date)) {
        auto_refund_rows_5_mins_ago_list.add(autoRefundList.get(i));
      } else if (date_judge_time.before(time_0_min_ago_date)) {
        auto_refund_rows_0_mins_ago_list.add(autoRefundList.get(i));
      }
    }

    String time_of_30_mins_ago = Common.formatDateToString(
      new Date(Common.formatToDate(time_of_cpl_auto_refund).getTime() - 30 * 60 * 1000));
    List<AutoRefund> auto_refund_rows_list_30_mins_ago = getCustomMapper()
      .get_auto_refund_data_with_time_limit_from_local_db(time_of_30_mins_ago, time_0_min_ago_str);

    String error_messages = "";
    int flag_left = auto_refund_rows_10_mins_ago_list.size();
    double flag_right = auto_refund_rows_list_30_mins_ago.size() * 1.0 / 6;
    if (flag_left / flag_right > 4700 / (99 * flag_right) + 124 / 99.0) {
      String queryLink = create_auto_refund_link(time_15_min_ago_str, time_10_min_ago_str);
      error_messages = "Auto-refund monitor:&nbsp;&nbsp;"
        + "count:" + flag_left + ", avg:" + Common.get_num_with_2_decimal(flag_right) + "#&"
        + queryLink;
    }
    if (error_messages.length() < 1) {
      flag_left = auto_refund_rows_5_mins_ago_list.size();
      if (flag_left / flag_right > 4700 / (99 * flag_right) + 124 / 99.0) {
        String queryLink = create_auto_refund_link(time_10_min_ago_str, time_5_min_ago_str);
        error_messages = "Auto-refund monitor:&nbsp;&nbsp;"
          + "count:" + flag_left + ", avg:" + Common.get_num_with_2_decimal(flag_right) + "#&"
          + queryLink;
      }
    }
    if (error_messages.length() < 1) {
      flag_left = auto_refund_rows_0_mins_ago_list.size();
      if (flag_left / flag_right > 4700 / (99 * flag_right) + 124 / 99.0) {
        String queryLink = create_auto_refund_link(time_5_min_ago_str, time_0_min_ago_str);
        error_messages = "Auto-refund monitor:&nbsp;&nbsp;"
          + "count:" + flag_left + ", avg:" + Common.get_num_with_2_decimal(flag_right) + "#&"
          + queryLink;
      }
    }

    return error_messages;
  }

  public String getTime(String dbName, String up_down) throws Exception {
    return getCustomMapper().getTime(dbName, up_down);
  }

  public String getLastUpdateTime(String fieldName) throws Exception {
    return getCustomMapper().getLastUpdateTime(fieldName);
  }

  /**
   * According to the time period to query all CPLISCRefundLogs.
   */
  public List<CPLISCRefundLog> getCPLISCRefundLogsByPeriod(String queryStr,
    CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().getCPLISCRefundLogsByPeriod(cplQueryVo.getStartDate(),
      cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  public List<CPLGatewaySelectionFailureForAutorenewal> getALLCPLAutorenewalLogsByPeriod(
    String queryStr,
    CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().getALLCPLAutorenewalLogsByPeriod(cplQueryVo.getStartDate(),
      cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  public List<CommonPurchaseLogWithSomeKeyFields> get_all_cpl_exception_monitor_logs_by_period(
    int response_code, int reason_code, String queryStr, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);
    List<CommonPurchaseLogWithSomeKeyFields> list = getCustomMapper()
      .get_cpl_exception_logs_by_sort(cplQueryVo.getStartDate(), cplQueryVo.getEndDate(),
        cplQueryVo.getStartIndex(), cplQueryVo.getAmount(), response_code, reason_code,
        cplQueryVo.getWhich_column(), cplQueryVo.getSort_way());

    return list;
  }

  public List<CommonPurchaseLogWithSomeKeyFields> get_EComm_error_logs_by_period(
    String queryStr, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);
    List<CommonPurchaseLogWithSomeKeyFields> list = getCustomMapper().get_EComm_error_logs_by_sort(cplQueryVo.getStartDate(), cplQueryVo.getEndDate(),cplQueryVo.getStartIndex(), cplQueryVo.getAmount(),cplQueryVo.getWhich_column(), cplQueryVo.getSort_way());

    return list;
  }

  public List<AutoRefund> get_cpl_autorefund_logs_by_period(String queryStr, CPLQueryVo cplQueryVo)
    throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);
    List<AutoRefund> list = getCustomMapper()
      .get_cpl_autorefund_logs_by_period(cplQueryVo.getStartDate(), cplQueryVo.getEndDate(),
        cplQueryVo.getStartIndex(), cplQueryVo.getAmount());

    return list;
  }

  public List<CommonPurchaseLogWithSomeKeyFields> get_all_cpl_exception_monitor_logs_by_period(
    int response_code, int reason_code, String start_date, String end_date, CPLQueryVo cplQueryVo)
    throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    List<CommonPurchaseLogWithSomeKeyFields> list = getCustomMapper()
      .get_all_cpl_exception_monitor_logs_by_period(cplQueryVo.getStartDate(),
        cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount(),
        response_code, reason_code);

    return list;
  }

  public List<CommonPurchaseLogWithSomeKeyFields> get_EComm_error_logs_by_period(
    String start_date, String end_date, CPLQueryVo cplQueryVo)throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    List<CommonPurchaseLogWithSomeKeyFields> list = getCustomMapper()
      .get_EComm_error_logs_by_period(cplQueryVo.getStartDate(),cplQueryVo.getEndDate(),
        cplQueryVo.getStartIndex(), cplQueryVo.getAmount());

    return list;
  }

  public List<AutoRefund> get_cpl_autorefund_logs_by_period(
    String start_date, String end_date, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    List<AutoRefund> list = getCustomMapper()
      .get_cpl_autorefund_logs_by_period(cplQueryVo.getStartDate(),
        cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount());

    return list;
  }

  public String get_error_description(int response_code, int reason_code) throws IOException {

    return getCustomMapper().get_error_description(response_code, reason_code);
  }

  /**
   * According to the time period to query all ErrorPurchaseLogs.
   */
  public List<ErrorPurchaseLog> getErrorPurchaseLogsByPeriod(String queryStr,
    CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().getErrorPurchaseLogsByPeriod(cplQueryVo.getStartDate(),
      cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  private CPLQueryVo parseQueryStr(String queryStr, CPLQueryVo cplQueryVo) throws ParseException {
    JSONObject params = new JSONObject(queryStr);

    String start_date = "";
    try {
      start_date = params.getString("start_date");
    } catch (JSONException e)  {
    }
    if (start_date != null && !"".equals(start_date)) {
      cplQueryVo.setStartDate(start_date);
    }

    String sort_way = "";
    try {
      sort_way = params.getString("sort_way");
    } catch (JSONException e) {
    }
    if ("".equalsIgnoreCase(sort_way)) {
      cplQueryVo.setSort_way("asc");
    } else {
      cplQueryVo.setSort_way(sort_way);
    }

    String which_column = "";
    try {
      which_column = params.getString("which_column");
    } catch (JSONException e) {
    }
    if ("".equalsIgnoreCase(which_column)) {
      cplQueryVo.setWhich_column(null);
    } else {
      cplQueryVo.setWhich_column(which_column);
    }

    String end_date = "";
    try {
      end_date = params.getString("end_date");
    } catch (JSONException e) {
    }
    if (end_date.equals("")) {
      cplQueryVo.setEndDate(null);
    } else if ("".equalsIgnoreCase(which_column)) {
      if (end_date.length() > 10) {
        cplQueryVo.setEndDate(end_date);
      } else {
        //Plus one day,so we can find this day record
        cplQueryVo.setEndDate(Common.increaseDate(end_date, 1));
      }
    } else {
      cplQueryVo.setEndDate(end_date);
    }

    return cplQueryVo;
  }

  public List<CPLGatewaySelectionFailureForAutorenewal> getCPLAutorenewalByLatestSnapshotId(
    CPLQueryVo cplQueryVo)
    throws Exception {

    return getCustomMapper().getCPLAutorenewalByLatestSnapshotId(cplQueryVo.getStartIndex(),
      cplQueryVo.getAmount());
  }

  /**
   * According to latest snapshotId to query CPLISCRefundLog.
   */
  public List<CPLISCRefundLog> getCPLISCRefundLogsByLatestSnapshotId(CPLQueryVo cplQueryVo)
    throws Exception {

    return getCustomMapper().getCPLISCRefundLogsByLatestSnapshotId(cplQueryVo.getStartIndex(),
      cplQueryVo.getAmount());
  }

  /**
   * According to latest snapshotId to query ErrorPurchaseLogs.
   */
  public List<ErrorPurchaseLog> getErrorPurchaseLogsByLatestSnapshotId(CPLQueryVo cplQueryVo)
    throws Exception {

    return getCustomMapper().getErrorPurchaseLogsByLatestSnapshotId(cplQueryVo.getStartIndex(),
      cplQueryVo.getAmount());
  }

  /**
   * Method to execute request from Slack.
   */
  public List<CPLISCRefundLog> getALLCPLISCRefundLogs(CPLQueryVo cplQueryVo) throws Exception {
    return getCustomMapper()
      .getALLCPLISCRefundLogs(cplQueryVo.getStartDate(), cplQueryVo.getEndDate(),
        cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  public List<CPLGatewaySelectionFailureForAutorenewal> getALLCPLAutorenewalLogs(
    CPLQueryVo cplQueryVo) throws Exception {

    return getCustomMapper().getALLCPLAutorenewalLogsByPeriod(cplQueryVo.getStartDate(),
      cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  public List<CommonPurchaseLogWithSomeKeyFields> getGatewayOutageDatas(
    String queryStr, CPLQueryVo cplQueryVo) throws Exception {
    if(queryStr!=null && !"".equals(queryStr)) {
      cplQueryVo = parseQueryStr(queryStr, cplQueryVo);
    }else{
      queryStr = "{}";
      cplQueryVo = parseQueryStr(queryStr, cplQueryVo);
    }

    return getCustomMapper().getGatewayOutageDatas(cplQueryVo.getStartIndex(),
      cplQueryVo.getAmount(),cplQueryVo.getWhich_column(), cplQueryVo.getSort_way());
  }

  public List<CPLGatewaySelectionFailureForAutorenewal> get_cpl_gateway_selection_failure_details_logs(
    CPLQueryVo cplQueryVo, String order_id, String shopper_id) throws Exception {

    return getCustomMapper()
      .get_cpl_gateway_selection_failure_details_logs(cplQueryVo.getStartDate(),
        cplQueryVo.getEndDate(), cplQueryVo.getStartIndex(), cplQueryVo.getAmount(), order_id,
        shopper_id);
  }

  /**
   * Method to execute request from Slack.
   */
  public List<ErrorPurchaseLog> getALLErrorPurchaseLogs(CPLQueryVo cplQueryVo) throws Exception {
    return getCustomMapper()
      .getAllErrorPurchaseLogs(cplQueryVo.getStartIndex(), cplQueryVo.getAmount());
  }

  /**
   * count error Purchase's number.
   */
  public int getCount(String dbName, CPLQueryVo cplQueryVo) throws Exception {
    return getCustomMapper().getCount(dbName, cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_cpl_gateway_selection_failure_details_count(String dbName, CPLQueryVo cplQueryVo,
    String order_id, String shopper_id) throws Exception {
    return getCustomMapper()
      .get_cpl_gateway_selection_failure_details_count(dbName, cplQueryVo.getStartDate(),
        cplQueryVo.getEndDate(), order_id, shopper_id);
  }

  /**
   * count error Purchase's number based on time period.
   */
  public int getCountByPeriod(String dbName, String queryStr, CPLQueryVo cplQueryVo)
    throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper()
      .getCountByPeriod(dbName, cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_cpl_exception_monitor_count_by_period(int response_code, int reason_code,
    String queryStr, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().get_cpl_exception_monitor_count_by_period(response_code, reason_code,
      cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_EComm_error_count_by_period(
    String queryStr, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().get_EComm_error_count_by_period(
      cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_cpl_autorefund_logs_count_by_period(String queryStr, CPLQueryVo cplQueryVo)
    throws Exception {
    cplQueryVo = parseQueryStr(queryStr, cplQueryVo);

    return getCustomMapper().get_cpl_autorefund_logs_count_by_period(
      cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_cpl_exception_monitor_count_by_period(int response_code, int reason_code,
    String start_date, String end_date, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    return getCustomMapper().get_cpl_exception_monitor_count_by_period(response_code, reason_code,
      cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_EComm_error_count_by_period(
    String start_date, String end_date, CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    return getCustomMapper().get_EComm_error_count_by_period(
      cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  public int get_cpl_autorefund_logs_count_by_period(String start_date, String end_date,
    CPLQueryVo cplQueryVo) throws Exception {
    cplQueryVo.setStartDate(start_date);
    cplQueryVo.setEndDate(end_date);

    return getCustomMapper()
      .get_cpl_autorefund_logs_count_by_period(cplQueryVo.getStartDate(), cplQueryVo.getEndDate());
  }

  /**
   * count CPLISCRefundLog's number based on latest SnapshotId.
   */
  public int getCPLISCRefundLogCountByLatestSnapshotId() throws Exception {

    return getCustomMapper().getCPLISCRefundLogCountByLatestSnapshotId();
  }

  public int getCPLAutorenewalCountByLatestSnapshotId() throws Exception {

    return getCustomMapper().getCPLAutorenewalCountByLatestSnapshotId();
  }

  /**
   * count error Purchase's number based on latest SnapshotId.
   */
  public int getErrorPurchaseCountByLatestSnapshotId() throws Exception {

    return getCustomMapper().getErrorPurchaseCountByLatestSnapshotId();
  }

  private String create_auto_refund_link(String start_time, String end_time) {
    return "http://paymentmon.cloud.phx3.gdg/MonitorTool/cpl_auto_refund_servlet?"
      + "startDate=" + Common.escapeTime(start_time) + "&endDate=" + Common.escapeTime(end_time);
  }


}
