package com.godaddy.ecomm.base.fulfillment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorOrderRequestXmlExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public ErrorOrderRequestXmlExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  protected abstract static class GeneratedCriteria {

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andErrorIdIsNull() {
      addCriterion("errorId is null");
      return (Criteria) this;
    }

    public Criteria andErrorIdIsNotNull() {
      addCriterion("errorId is not null");
      return (Criteria) this;
    }

    public Criteria andErrorIdEqualTo(Integer value) {
      addCriterion("errorId =", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdNotEqualTo(Integer value) {
      addCriterion("errorId <>", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdGreaterThan(Integer value) {
      addCriterion("errorId >", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("errorId >=", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdLessThan(Integer value) {
      addCriterion("errorId <", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdLessThanOrEqualTo(Integer value) {
      addCriterion("errorId <=", value, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdIn(List<Integer> values) {
      addCriterion("errorId in", values, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdNotIn(List<Integer> values) {
      addCriterion("errorId not in", values, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdBetween(Integer value1, Integer value2) {
      addCriterion("errorId between", value1, value2, "errorId");
      return (Criteria) this;
    }

    public Criteria andErrorIdNotBetween(Integer value1, Integer value2) {
      addCriterion("errorId not between", value1, value2, "errorId");
      return (Criteria) this;
    }

    public Criteria andOrder_idIsNull() {
      addCriterion("order_id is null");
      return (Criteria) this;
    }

    public Criteria andOrder_idIsNotNull() {
      addCriterion("order_id is not null");
      return (Criteria) this;
    }

    public Criteria andOrder_idEqualTo(Integer value) {
      addCriterion("order_id =", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idNotEqualTo(Integer value) {
      addCriterion("order_id <>", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idGreaterThan(Integer value) {
      addCriterion("order_id >", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idGreaterThanOrEqualTo(Integer value) {
      addCriterion("order_id >=", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idLessThan(Integer value) {
      addCriterion("order_id <", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idLessThanOrEqualTo(Integer value) {
      addCriterion("order_id <=", value, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idIn(List<Integer> values) {
      addCriterion("order_id in", values, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idNotIn(List<Integer> values) {
      addCriterion("order_id not in", values, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idBetween(Integer value1, Integer value2) {
      addCriterion("order_id between", value1, value2, "order_id");
      return (Criteria) this;
    }

    public Criteria andOrder_idNotBetween(Integer value1, Integer value2) {
      addCriterion("order_id not between", value1, value2, "order_id");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdIsNull() {
      addCriterion("privateLabelId is null");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdIsNotNull() {
      addCriterion("privateLabelId is not null");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdEqualTo(Integer value) {
      addCriterion("privateLabelId =", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdNotEqualTo(Integer value) {
      addCriterion("privateLabelId <>", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdGreaterThan(Integer value) {
      addCriterion("privateLabelId >", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("privateLabelId >=", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdLessThan(Integer value) {
      addCriterion("privateLabelId <", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdLessThanOrEqualTo(Integer value) {
      addCriterion("privateLabelId <=", value, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdIn(List<Integer> values) {
      addCriterion("privateLabelId in", values, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdNotIn(List<Integer> values) {
      addCriterion("privateLabelId not in", values, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdBetween(Integer value1, Integer value2) {
      addCriterion("privateLabelId between", value1, value2, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andPrivateLabelIdNotBetween(Integer value1, Integer value2) {
      addCriterion("privateLabelId not between", value1, value2, "privateLabelId");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNull() {
      addCriterion("createDate is null");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNotNull() {
      addCriterion("createDate is not null");
      return (Criteria) this;
    }

    public Criteria andCreateDateEqualTo(Date value) {
      addCriterion("createDate =", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotEqualTo(Date value) {
      addCriterion("createDate <>", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThan(Date value) {
      addCriterion("createDate >", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
      addCriterion("createDate >=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThan(Date value) {
      addCriterion("createDate <", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThanOrEqualTo(Date value) {
      addCriterion("createDate <=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateIn(List<Date> values) {
      addCriterion("createDate in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotIn(List<Date> values) {
      addCriterion("createDate not in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateBetween(Date value1, Date value2) {
      addCriterion("createDate between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotBetween(Date value1, Date value2) {
      addCriterion("createDate not between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlIsNull() {
      addCriterion("processCtrl is null");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlIsNotNull() {
      addCriterion("processCtrl is not null");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlEqualTo(String value) {
      addCriterion("processCtrl =", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlNotEqualTo(String value) {
      addCriterion("processCtrl <>", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlGreaterThan(String value) {
      addCriterion("processCtrl >", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlGreaterThanOrEqualTo(String value) {
      addCriterion("processCtrl >=", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlLessThan(String value) {
      addCriterion("processCtrl <", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlLessThanOrEqualTo(String value) {
      addCriterion("processCtrl <=", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlLike(String value) {
      addCriterion("processCtrl like", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlNotLike(String value) {
      addCriterion("processCtrl not like", value, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlIn(List<String> values) {
      addCriterion("processCtrl in", values, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlNotIn(List<String> values) {
      addCriterion("processCtrl not in", values, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlBetween(String value1, String value2) {
      addCriterion("processCtrl between", value1, value2, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andProcessCtrlNotBetween(String value1, String value2) {
      addCriterion("processCtrl not between", value1, value2, "processCtrl");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdIsNull() {
      addCriterion("api_orderRequestStatusId is null");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdIsNotNull() {
      addCriterion("api_orderRequestStatusId is not null");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdEqualTo(Integer value) {
      addCriterion("api_orderRequestStatusId =", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdNotEqualTo(Integer value) {
      addCriterion("api_orderRequestStatusId <>", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdGreaterThan(Integer value) {
      addCriterion("api_orderRequestStatusId >", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("api_orderRequestStatusId >=", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdLessThan(Integer value) {
      addCriterion("api_orderRequestStatusId <", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdLessThanOrEqualTo(Integer value) {
      addCriterion("api_orderRequestStatusId <=", value, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdIn(List<Integer> values) {
      addCriterion("api_orderRequestStatusId in", values, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdNotIn(List<Integer> values) {
      addCriterion("api_orderRequestStatusId not in", values, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdBetween(Integer value1, Integer value2) {
      addCriterion("api_orderRequestStatusId between", value1, value2, "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andApi_orderRequestStatusIdNotBetween(Integer value1, Integer value2) {
      addCriterion("api_orderRequestStatusId not between", value1, value2,
          "api_orderRequestStatusId");
      return (Criteria) this;
    }

    public Criteria andMessageTextIsNull() {
      addCriterion("messageText is null");
      return (Criteria) this;
    }

    public Criteria andMessageTextIsNotNull() {
      addCriterion("messageText is not null");
      return (Criteria) this;
    }

    public Criteria andMessageTextEqualTo(String value) {
      addCriterion("messageText =", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextNotEqualTo(String value) {
      addCriterion("messageText <>", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextGreaterThan(String value) {
      addCriterion("messageText >", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextGreaterThanOrEqualTo(String value) {
      addCriterion("messageText >=", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextLessThan(String value) {
      addCriterion("messageText <", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextLessThanOrEqualTo(String value) {
      addCriterion("messageText <=", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextLike(String value) {
      addCriterion("messageText like", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextNotLike(String value) {
      addCriterion("messageText not like", value, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextIn(List<String> values) {
      addCriterion("messageText in", values, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextNotIn(List<String> values) {
      addCriterion("messageText not in", values, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextBetween(String value1, String value2) {
      addCriterion("messageText between", value1, value2, "messageText");
      return (Criteria) this;
    }

    public Criteria andMessageTextNotBetween(String value1, String value2) {
      addCriterion("messageText not between", value1, value2, "messageText");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSIsNull() {
      addCriterion("orderXMLDBCS is null");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSIsNotNull() {
      addCriterion("orderXMLDBCS is not null");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSEqualTo(String value) {
      addCriterion("orderXMLDBCS =", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSNotEqualTo(String value) {
      addCriterion("orderXMLDBCS <>", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSGreaterThan(String value) {
      addCriterion("orderXMLDBCS >", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSGreaterThanOrEqualTo(String value) {
      addCriterion("orderXMLDBCS >=", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSLessThan(String value) {
      addCriterion("orderXMLDBCS <", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSLessThanOrEqualTo(String value) {
      addCriterion("orderXMLDBCS <=", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSLike(String value) {
      addCriterion("orderXMLDBCS like", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSNotLike(String value) {
      addCriterion("orderXMLDBCS not like", value, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSIn(List<String> values) {
      addCriterion("orderXMLDBCS in", values, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSNotIn(List<String> values) {
      addCriterion("orderXMLDBCS not in", values, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSBetween(String value1, String value2) {
      addCriterion("orderXMLDBCS between", value1, value2, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andOrderXMLDBCSNotBetween(String value1, String value2) {
      addCriterion("orderXMLDBCS not between", value1, value2, "orderXMLDBCS");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToIsNull() {
      addCriterion("gdshop_termsOfServiceAgreedTo is null");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToIsNotNull() {
      addCriterion("gdshop_termsOfServiceAgreedTo is not null");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToEqualTo(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo =", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToNotEqualTo(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo <>", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToGreaterThan(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo >", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToGreaterThanOrEqualTo(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo >=", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToLessThan(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo <", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToLessThanOrEqualTo(Boolean value) {
      addCriterion("gdshop_termsOfServiceAgreedTo <=", value, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToIn(List<Boolean> values) {
      addCriterion("gdshop_termsOfServiceAgreedTo in", values, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToNotIn(List<Boolean> values) {
      addCriterion("gdshop_termsOfServiceAgreedTo not in", values, "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToBetween(Boolean value1, Boolean value2) {
      addCriterion("gdshop_termsOfServiceAgreedTo between", value1, value2,
          "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andGdshop_termsOfServiceAgreedToNotBetween(Boolean value1, Boolean value2) {
      addCriterion("gdshop_termsOfServiceAgreedTo not between", value1, value2,
          "gdshop_termsOfServiceAgreedTo");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idIsNull() {
      addCriterion("snapshot_id is null");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idIsNotNull() {
      addCriterion("snapshot_id is not null");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idEqualTo(Integer value) {
      addCriterion("snapshot_id =", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idNotEqualTo(Integer value) {
      addCriterion("snapshot_id <>", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idGreaterThan(Integer value) {
      addCriterion("snapshot_id >", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idGreaterThanOrEqualTo(Integer value) {
      addCriterion("snapshot_id >=", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idLessThan(Integer value) {
      addCriterion("snapshot_id <", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idLessThanOrEqualTo(Integer value) {
      addCriterion("snapshot_id <=", value, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idIn(List<Integer> values) {
      addCriterion("snapshot_id in", values, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idNotIn(List<Integer> values) {
      addCriterion("snapshot_id not in", values, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idBetween(Integer value1, Integer value2) {
      addCriterion("snapshot_id between", value1, value2, "snapshot_id");
      return (Criteria) this;
    }

    public Criteria andSnapshot_idNotBetween(Integer value1, Integer value2) {
      addCriterion("snapshot_id not between", value1, value2, "snapshot_id");
      return (Criteria) this;
    }
  }

  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}