package com.godaddy.ecomm.base.cpl;

/**
 * this class corresponding to the database table.
 */
public class CommonPurchaseLogWithSomeKeyFields {

  private int errorid;
  private int cpl_id;
  private String order_id;
  private String shopper_id;
  private int total;
  private int reason_code;
  private int response_code;
  private int attempts;
  private String date_entered;
  private String source;
  private int privateLabelID;
  private int response_time;
  private String processor;
  private String payment;
  private String payment_type;
  private String billing_country_code;

  // According to this table:[gatewayActionType], the specific description is displayed
  //private int gatewayactiontype;
  private String gatewayActionType;

  private String basket_type;
  private String ipaddress;
  private int pkid;
  private String transactionCurrencyType;
  private int transactioncurrencytotal;
  private int pp_shopperpaymentmethodid;

  public CommonPurchaseLogWithSomeKeyFields() {
  }

  public CommonPurchaseLogWithSomeKeyFields(int errorid, int cpl_id, String order_id,
    String shopper_id, int total, int reason_code, int response_code, int attempts,
    String date_entered, String source, int privateLabelID, int response_time,
    String processor, String payment, String payment_type, String billing_country_code,
      String gatewayActionType, String basket_type, String ipaddress, int pkid,
    String transactionCurrencyType, int transactioncurrencytotal, int pp_shopperpaymentmethodid) {
    this.errorid = errorid;
    this.cpl_id = cpl_id;
    this.order_id = order_id;
    this.shopper_id = shopper_id;
    this.total = total;
    this.reason_code = reason_code;
    this.response_code = response_code;
    this.attempts = attempts;
    this.date_entered = date_entered;
    this.source = source;
    this.privateLabelID = privateLabelID;
    this.response_time = response_time;
    this.processor = processor;
    this.payment = payment;
    this.payment_type = payment_type;
    this.billing_country_code = billing_country_code;
    this.gatewayActionType = gatewayActionType;
    this.basket_type = basket_type;
    this.ipaddress = ipaddress;
    this.pkid = pkid;
    this.transactionCurrencyType = transactionCurrencyType;
    this.transactioncurrencytotal = transactioncurrencytotal;
    this.pp_shopperpaymentmethodid = pp_shopperpaymentmethodid;
  }

  public int getErrorid() {
    return errorid;
  }

  public void setErrorid(int errorid) {
    this.errorid = errorid;
  }

  public int getCpl_id() {
    return cpl_id;
  }

  public void setCpl_id(int cpl_id) {
    this.cpl_id = cpl_id;
  }

  public String getOrder_id() {
    return order_id;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public String getShopper_id() {
    return shopper_id;
  }

  public void setShopper_id(String shopper_id) {
    this.shopper_id = shopper_id;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
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

  public int getAttempts() {
    return attempts;
  }

  public void setAttempts(int attempts) {
    this.attempts = attempts;
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

  public int getPrivateLabelID() {
    return privateLabelID;
  }

  public void setPrivateLabelID(int privateLabelID) {
    this.privateLabelID = privateLabelID;
  }

  public int getResponse_time() {
    return response_time;
  }

  public void setResponse_time(int response_time) {
    this.response_time = response_time;
  }

  public String getProcessor() {
    return processor;
  }

  public void setProcessor(String processor) {
    this.processor = processor;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public String getPayment_type() {
    return payment_type;
  }

  public void setPayment_type(String payment_type) {
    this.payment_type = payment_type;
  }

  public String getBilling_country_code() {
    return billing_country_code;
  }

  public void setBilling_country_code(String billing_country_code) {
    this.billing_country_code = billing_country_code;
  }

  public String getGatewayActionType() {
    return gatewayActionType;
  }

  public void setGatewayActionType(String gatewayActionType) {
    this.gatewayActionType = gatewayActionType;
  }

  public String getBasket_type() {
    return basket_type;
  }

  public void setBasket_type(String basket_type) {
    this.basket_type = basket_type;
  }

  public String getIpaddress() {
    return ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }

  public int getPkid() {
    return pkid;
  }

  public void setPkid(int pkid) {
    this.pkid = pkid;
  }

  public String getTransactionCurrencyType() {
    return transactionCurrencyType;
  }

  public void setTransactionCurrencyType(String transactionCurrencyType) {
    this.transactionCurrencyType = transactionCurrencyType;
  }

  public int getTransactioncurrencytotal() {
    return transactioncurrencytotal;
  }

  public void setTransactioncurrencytotal(int transactioncurrencytotal) {
    this.transactioncurrencytotal = transactioncurrencytotal;
  }

  public int getPp_shopperpaymentmethodid() {
    return pp_shopperpaymentmethodid;
  }

  public void setPp_shopperpaymentmethodid(int pp_shopperpaymentmethodid) {
    this.pp_shopperpaymentmethodid = pp_shopperpaymentmethodid;
  }

  @Override
  public String toString() {
    return "CommonPurchaseLogWithSomeKeyFields{" +
      "errorid=" + errorid +
      ", cpl_id=" + cpl_id +
      ", order_id='" + order_id + '\'' +
      ", shopper_id='" + shopper_id + '\'' +
      ", total=" + total +
      ", reason_code=" + reason_code +
      ", response_code=" + response_code +
      ", attempts=" + attempts +
      ", date_entered='" + date_entered + '\'' +
      ", source='" + source + '\'' +
      ", privateLabelID=" + privateLabelID +
      ", response_time=" + response_time +
      ", processor='" + processor + '\'' +
      ", payment='" + payment + '\'' +
      ", payment_type='" + payment_type + '\'' +
      ", billing_country_code='" + billing_country_code + '\'' +
      ", gatewayActionType=" + gatewayActionType +
      ", basket_type='" + basket_type + '\'' +
      ", ipaddress='" + ipaddress + '\'' +
      ", pkid=" + pkid +
      ", transactionCurrencyType='" + transactionCurrencyType + '\'' +
      ", transactioncurrencytotal=" + transactioncurrencytotal +
      ", pp_shopperpaymentmethodid=" + pp_shopperpaymentmethodid +
      '}';
  }
}
