package com.godaddy.ecomm.base.cpl;

/**
 * object model for CPL feature.
 */
public class ErrorPurchaseLog {

  private String order_id;
  private int shopper_id;
  private String date_entered;
  private String source;
  private String processor;
  private String gateway_raw;
  private int errorId;
  private int snapshot_Id;
  private String snapshotTime;

  public String getSnapshotTime() {
    return snapshotTime;
  }

  public void setSnapshotTime(String snapshotTime) {
    this.snapshotTime = snapshotTime;
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

  @Override
  public String toString() {
    return "ErrorPurchaseLog{" +
        "order_id=" + order_id +
        ", shopper_id=" + shopper_id +
        ", date_entered='" + date_entered + '\'' +
        ", source='" + source + '\'' +
        ", processor='" + processor + '\'' +
        ", gateway_raw='" + gateway_raw + '\'' +
        ", errorId=" + errorId +
        ", snapshot_Id=" + snapshot_Id +
        '}';
  }
}
