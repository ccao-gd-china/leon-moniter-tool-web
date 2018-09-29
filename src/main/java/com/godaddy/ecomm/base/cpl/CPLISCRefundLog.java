package com.godaddy.ecomm.base.cpl;

public class CPLISCRefundLog {

  private String order_id;
  private int shopper_id;
  private String date_entered;
  private int reason_code;
  private int response_code;
  private String source;
  private String processor;
  private String gateway_raw;
  private int gatewayActionType;
  private int errorId;
  private int snapshot_Id;
  private String distinct_concat;

  public CPLISCRefundLog() {
  }

  public CPLISCRefundLog(String order_id, int shopper_id, String date_entered, int reason_code,
      int response_code, String source, String processor, String gateway_raw, int gatewayActionType,
      int errorId, int snapshot_Id, String distinct_concat) {
    this.order_id = order_id;
    this.shopper_id = shopper_id;
    this.date_entered = date_entered;
    this.reason_code = reason_code;
    this.response_code = response_code;
    this.source = source;
    this.processor = processor;
    this.gateway_raw = gateway_raw;
    this.gatewayActionType = gatewayActionType;
    this.errorId = errorId;
    this.snapshot_Id = snapshot_Id;
    this.distinct_concat = distinct_concat;
  }

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public int getShopper_id() {
    return shopper_id;
  }

  public void setShopper_id(int shopper_id) {
    this.shopper_id = shopper_id;
  }

  public String getDate_entered() {
    return date_entered;
  }

  public void setDate_entered(String date_entered) {
    this.date_entered = date_entered;
  }

  public int getReason_code() {
    return reason_code;
  }

  public void setReason_code(int reason_code) {
    this.reason_code = reason_code;
  }

  public int getResponse_code() {
    return response_code;
  }

  public void setResponse_code(int response_code) {
    this.response_code = response_code;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getProcessor() {
    return processor;
  }

  public void setProcessor(String processor) {
    this.processor = processor;
  }

  public String getGateway_raw() {
    return gateway_raw;
  }

  public void setGateway_raw(String gateway_raw) {
    this.gateway_raw = gateway_raw;
  }

  public int getGatewayActionType() {
    return gatewayActionType;
  }

  public void setGatewayActionType(int gatewayActionType) {
    this.gatewayActionType = gatewayActionType;
  }

  public int getErrorId() {
    return errorId;
  }

  public void setErrorId(int errorId) {
    this.errorId = errorId;
  }

  public int getSnapshot_Id() {
    return snapshot_Id;
  }

  public void setSnapshot_Id(int snapshot_Id) {
    this.snapshot_Id = snapshot_Id;
  }

  public String getDistinct_concat() {
    return distinct_concat;
  }

  public void setDistinct_concat(String distinct_concat) {
    this.distinct_concat = distinct_concat;
  }

  @Override
  public String toString() {
    return "CPLISCRefundLog{" +
        "order_id=" + order_id +
        ", shopper_id=" + shopper_id +
        ", date_entered='" + date_entered + '\'' +
        ", reason_code=" + reason_code +
        ", response_code=" + response_code +
        ", source='" + source + '\'' +
        ", processor='" + processor + '\'' +
        ", gateway_raw='" + gateway_raw + '\'' +
        ", gatewayActionType=" + gatewayActionType +
        ", errorId=" + errorId +
        ", snapshot_Id=" + snapshot_Id +
        ", distinct_concat='" + distinct_concat + '\'' +
        '}';
  }
}
