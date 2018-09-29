package com.godaddy.ecomm.base.fulfillment;


import java.util.Date;

public class ErrorOrderRequestXml extends OrderRequestXml {

  private Integer errorId;

  private Integer snapshot_id;

  private Date snapshotTime;

  private String severity;

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public Integer getErrorId() {
    return errorId;
  }

  public void setErrorId(Integer errorId) {
    this.errorId = errorId;
  }

  public Integer getSnapshot_id() {
    return snapshot_id;
  }

  public void setSnapshot_id(Integer snapshot_id) {
    this.snapshot_id = snapshot_id;
  }

  public Date getSnapshotTime() {
    return snapshotTime;
  }

  public void setSnapshotTime(Date snapshotTime) {
    this.snapshotTime = snapshotTime;
  }
}
