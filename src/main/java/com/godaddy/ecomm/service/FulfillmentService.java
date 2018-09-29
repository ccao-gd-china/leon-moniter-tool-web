package com.godaddy.ecomm.service;

import com.godaddy.ecomm.base.cpl.CommonPurchaseLogWithSomeKeyFields;
import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import com.godaddy.ecomm.base.fulfillment.FulfillmentQueryVo;
import com.godaddy.ecomm.dao.fulfillment.ErrorOrderRequestXmlCustomMapper;
import com.godaddy.ecomm.utils.Common;
import com.godaddy.ecomm.utils.DataSourceEnvironment;
import com.godaddy.ecomm.utils.MapperFactory;

import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class FulfillmentService {

  private Logger logger = LoggerFactory.getLogger(FulfillmentService.class);

  private ErrorOrderRequestXmlCustomMapper getCustomMapper() throws IOException {
    return MapperFactory.
      createMapper(ErrorOrderRequestXmlCustomMapper.class, DataSourceEnvironment.Local);
  }

  /**
   * Method to execute request from Slack.
   */
  public int getErrorOrderRequestsNumber(FulfillmentQueryVo fulfillmentQueryVo) throws Exception {

    return getCustomMapper().getErrorOrderRequestsNum(fulfillmentQueryVo.getSnapshot_id(),
      fulfillmentQueryVo.getStartDate(), fulfillmentQueryVo.getEndDate(),
      fulfillmentQueryVo.getPrivateLabelId(), fulfillmentQueryVo.getFilterMessage(),
      fulfillmentQueryVo.getFilterPLID(), fulfillmentQueryVo.getFilterSeverity());
  }

  /**
   * Method to execute request from page.
   */
  public int getErrorOrderRequestsNumber(String queryStr, FulfillmentQueryVo fulfillmentQueryVo)
    throws Exception {
    fulfillmentQueryVo = parseQueryStr(queryStr, fulfillmentQueryVo);

    return getErrorOrderRequestsNumber(fulfillmentQueryVo);
  }

  public int getLatestSnapshot_id() throws Exception {

    return getCustomMapper().getLatestSnapshot_id();
  }

  /**
   * Method to execute request from Slack.
   */
  public List<ErrorOrderRequestXml> getErrorOrderRequests(FulfillmentQueryVo fulfillmentQueryVo)
    throws Exception {

    return getCustomMapper().getErrorOrderRequests(fulfillmentQueryVo.getSnapshot_id(),
      fulfillmentQueryVo.getStartDate(),
      fulfillmentQueryVo.getEndDate(), fulfillmentQueryVo.getPrivateLabelId(),
      fulfillmentQueryVo.getStartIndex(), fulfillmentQueryVo.getAmount(), fulfillmentQueryVo.getFilterMessage(),
      fulfillmentQueryVo.getFilterPLID(), fulfillmentQueryVo.getFilterSeverity(), fulfillmentQueryVo.getSortByMessage(),
      fulfillmentQueryVo.getSortByPLID(), fulfillmentQueryVo.getSortBySeverity());
  }

  public List<ErrorOrderRequestXml> getLatestErrorOrderRequests(
    FulfillmentQueryVo fulfillmentQueryVo)
    throws Exception {

    return getCustomMapper().getLatestErrorOrderRequests(
      fulfillmentQueryVo.getStartIndex(), fulfillmentQueryVo.getAmount());
  }

  /**
   * Method to execute request from page.
   */
  public List<ErrorOrderRequestXml> getErrorOrderRequests(String queryStr,
                                                          FulfillmentQueryVo fulfillmentQueryVo) throws Exception {
    fulfillmentQueryVo = parseQueryStr(queryStr, fulfillmentQueryVo);

    return getErrorOrderRequests(fulfillmentQueryVo);
  }

  private FulfillmentQueryVo parseQueryStr(String queryStr, FulfillmentQueryVo fulfillmentQueryVo)
    throws ParseException {
    JSONObject params = new JSONObject(queryStr);

    int snapshotId = 0;
    try {
      snapshotId = params.getInt("snapshotId");
    } catch (JSONException e) {
      logger.info("Get From The Latest Snapshot ID");
    }
    fulfillmentQueryVo.setSnapshot_id(snapshotId);

    int privateLabel_id = 0;
    try {
      privateLabel_id = params.getInt("privateLabel_id");
    } catch (JSONException e) {
      logger.info("Get From All Private Label IDs");
    }
    fulfillmentQueryVo.setPrivateLabelId(privateLabel_id);

    String start_date = params.getString("start_date");
    if (start_date.equals("")) {
      fulfillmentQueryVo.setStartDate(null);
    } else {
      fulfillmentQueryVo.setStartDate(start_date);
    }

    String end_date = params.getString("end_date");
    if (end_date.equals("")) {
      fulfillmentQueryVo.setEndDate(null);
    } else {
      //Plus one day,so we can find this day record
      fulfillmentQueryVo.setEndDate(Common.increaseDate(end_date, 1));
    }

    try {
      fulfillmentQueryVo.setSortBySeverity(params.getInt("sort_severity"));
    } catch (JSONException e) {
    }

    try {
      fulfillmentQueryVo.setSortByMessage(params.getInt("sort_message"));
    } catch (JSONException e) {
    }

    try {
      fulfillmentQueryVo.setSortByPLID(params.getInt("sort_plid"));
    } catch (JSONException e) {
    }

    String filterSeverity = params.getString("filter_severity");
    if (filterSeverity.equals("")) {
      fulfillmentQueryVo.setFilterSeverity(null);
    } else {
      fulfillmentQueryVo.setFilterSeverity(filterSeverity);
    }

    String filterPlid = params.getString("filter_plid");
    if (filterSeverity.equals("")) {
      fulfillmentQueryVo.setFilterPLID(null);
    } else {
      fulfillmentQueryVo.setFilterPLID(filterPlid);
    }

    String filterMessage = params.getString("filter_message");
    if (filterSeverity.equals("")) {
      fulfillmentQueryVo.setFilterMessage(null);
    } else {
      fulfillmentQueryVo.setFilterMessage("%" + filterMessage + "%");
    }

    return fulfillmentQueryVo;
  }

  public JSONArray getAllPrivateLabelId(int snapshotId) throws Exception {
    List<String> privateLabelIds = getCustomMapper().getAllPrivateLabelId(snapshotId);

    JSONArray labelIDs = new JSONArray();
    for (String labelId : privateLabelIds) {
      labelIDs.put(labelId);
    }

    return labelIDs;
  }


}
