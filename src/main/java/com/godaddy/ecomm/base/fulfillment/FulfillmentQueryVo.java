package com.godaddy.ecomm.base.fulfillment;

public class FulfillmentQueryVo {

  int snapshot_id = 0;
  String startDate;
  String endDate;
  int startIndex;
  int amount;
  int privateLabelId = 0;
  int sortBySeverity = 0;
  int sortByPLID = 0;
  int sortByMessage = 0;
  String filterSeverity;
  String filterPLID;
  String filterMessage;

  public int getSortBySeverity() {
    return sortBySeverity;
  }

  public void setSortBySeverity(int sortBySeverity) {
    this.sortBySeverity = sortBySeverity;
  }

  public int getSortByPLID() {
    return sortByPLID;
  }

  public void setSortByPLID(int sortByPLID) {
    this.sortByPLID = sortByPLID;
  }

  public int getSortByMessage() {
    return sortByMessage;
  }

  public void setSortByMessage(int sortByMessage) {
    this.sortByMessage = sortByMessage;
  }

  public String getFilterSeverity() {
    return filterSeverity;
  }

  public void setFilterSeverity(String filterSeverity) {
    this.filterSeverity = filterSeverity;
  }

  public String getFilterPLID() {
    return filterPLID;
  }

  public void setFilterPLID(String filterPLID) {
    this.filterPLID = filterPLID;
  }

  public String getFilterMessage() {
    return filterMessage;
  }

  public void setFilterMessage(String filterMessage) {
    this.filterMessage = filterMessage;
  }

  public int getPrivateLabelId() {
    return privateLabelId;
  }

  public void setPrivateLabelId(int privateLabelId) {
    this.privateLabelId = privateLabelId;
  }

  public int getSnapshot_id() {
    return snapshot_id;
  }

  public void setSnapshot_id(int snapshot_id) {
    this.snapshot_id = snapshot_id;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
