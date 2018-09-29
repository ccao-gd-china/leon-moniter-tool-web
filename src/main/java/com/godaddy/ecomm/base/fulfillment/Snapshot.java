package com.godaddy.ecomm.base.fulfillment;

import java.util.Date;

public class Snapshot {

  private Integer snapshot_id;

  private Date snapshotTime;

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