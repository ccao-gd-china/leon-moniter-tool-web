package com.godaddy.ecomm.base.cpl;

public class CPLQueryVo {

  int snapshot_id = 0;
  int startIndex;
  int amount;
  String startDate;
  String endDate;
  String which_column;
  String sort_way;


  public String getSort_way() {
    return sort_way;
  }

  public void setSort_way(String sort_way) {
    this.sort_way = sort_way;
  }

  public String getWhich_column() {
    return which_column;
  }

  public void setWhich_column(String which_column) {
    this.which_column = which_column;
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

  public int getSnapshot_id() {
    return snapshot_id;
  }

  public void setSnapshot_id(int snapshot_id) {
    this.snapshot_id = snapshot_id;
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
