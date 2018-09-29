package com.godaddy.ecomm.base.cpl;

public class AutoRefund {

  private int errorid;
  private String shopper_id;
  private String order_id;
  private String prior_time;
  private String refund_id;
  private String refund_time;
  private String analyze;

  public AutoRefund() {
  }

  public AutoRefund(int errorid, String shopper_id, String order_id, String prior_time,
    String refund_id, String refund_time, String analyze) {
    this.errorid = errorid;
    this.shopper_id = shopper_id;
    this.order_id = order_id;
    this.prior_time = prior_time;
    this.refund_id = refund_id;
    this.refund_time = refund_time;
    this.analyze = analyze;
  }

  public int getErrorid() {
    return errorid;
  }

  public void setErrorid(int errorid) {
    this.errorid = errorid;
  }

  public String getShopper_id() {
    return shopper_id;
  }

  public void setShopper_id(String shopper_id) {
    this.shopper_id = shopper_id;
  }

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public String getPrior_time() {
    return prior_time;
  }

  public void setPrior_time(String prior_time) {
    this.prior_time = prior_time;
  }

  public String getRefund_id() {
    return refund_id;
  }

  public void setRefund_id(String refund_id) {
    this.refund_id = refund_id;
  }

  public String getRefund_time() {
    return refund_time;
  }

  public void setRefund_time(String refund_time) {
    this.refund_time = refund_time;
  }

  public String getAnalyze() {
    return analyze;
  }

  public void setAnalyze(String analyze) {
    this.analyze = analyze;
  }

  @Override
  public String toString() {
    return "AutoRefund{" +
      "errorid=" + errorid +
      ", shopper_id='" + shopper_id + '\'' +
      ", order_id='" + order_id + '\'' +
      ", prior_time='" + prior_time + '\'' +
      ", refund_id='" + refund_id + '\'' +
      ", refund_time='" + refund_time + '\'' +
      ", analyze='" + analyze + '\'' +
      '}';
  }
}
