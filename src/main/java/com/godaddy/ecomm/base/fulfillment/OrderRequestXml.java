package com.godaddy.ecomm.base.fulfillment;


import java.util.Date;

public class OrderRequestXml {

  private int order_id;

  private int privateLabelId;

  private Date createDate;

  private String processCtrl;

  private String orderXML;

  private int api_orderRequestStatusId;

  private String messageText;

  private String orderXMLDBCS;

  private boolean gdshop_termsOfServiceAgreedTo;

  public int getOrder_id() {
    return order_id;
  }

  public void setOrder_id(int order_id) {
    this.order_id = order_id;
  }

  public int getPrivateLabelId() {
    return privateLabelId;
  }

  public void setPrivateLabelId(int privateLabelId) {
    this.privateLabelId = privateLabelId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getProcessCtrl() {
    return processCtrl;
  }

  public void setProcessCtrl(String processCtrl) {
    this.processCtrl = processCtrl;
  }

  public String getOrderXML() {
    return orderXML;
  }

  public void setOrderXML(String orderXML) {
    this.orderXML = orderXML;
  }

  public int getApi_orderRequestStatusId() {
    return api_orderRequestStatusId;
  }

  public void setApi_orderRequestStatusId(int api_orderRequestStatusId) {
    this.api_orderRequestStatusId = api_orderRequestStatusId;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public String getOrderXMLDBCS() {
    return orderXMLDBCS;
  }

  public void setOrderXMLDBCS(String orderXMLDBCS) {
    this.orderXMLDBCS = orderXMLDBCS;
  }

  public boolean isGdshop_termsOfServiceAgreedTo() {
    return gdshop_termsOfServiceAgreedTo;
  }

  public void setGdshop_termsOfServiceAgreedTo(boolean gdshop_termsOfServiceAgreedTo) {
    this.gdshop_termsOfServiceAgreedTo = gdshop_termsOfServiceAgreedTo;
  }

  @Override
  public String toString() {
    return "OrderRequestXml{" +
        "order_id=" + order_id +
        ", privateLabelId=" + privateLabelId +
        ", createDate=" + createDate +
        ", processCtrl='" + processCtrl + '\'' +
        ", orderXML='" + orderXML + '\'' +
        ", api_orderRequestStatusId=" + api_orderRequestStatusId +
        ", messageText='" + messageText + '\'' +
        ", orderXMLDBCS='" + orderXMLDBCS + '\'' +
        ", gdshop_termsOfServiceAgreedTo=" + gdshop_termsOfServiceAgreedTo +
        '}';
  }
}
