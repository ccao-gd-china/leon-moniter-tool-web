package com.godaddy.ecomm.base.cpl;

/**
 * this class corresponding to the local database table.
 */
public class CommonResponseReason {

  private int response_code;
  private int reason_code;
  private String error_description;
  private int pkid;

  public CommonResponseReason() {}

  public CommonResponseReason(int response_code, int reason_code, String error_description,
    int pkid) {
    this.response_code = response_code;
    this.reason_code = reason_code;
    this.error_description = error_description;
    this.pkid = pkid;
  }

  public int getResponse_code() {
    return response_code;
  }

  public void setResponse_code(int response_code) {
    this.response_code = response_code;
  }

  public int getReason_code() {
    return reason_code;
  }

  public void setReason_code(int reason_code) {
    this.reason_code = reason_code;
  }

  public String getError_description() {
    return error_description;
  }

  public void setError_description(String error_description) {
    this.error_description = error_description;
  }

  public int getPkid() {
    return pkid;
  }

  public void setPkid(int pkid) {
    this.pkid = pkid;
  }

  @Override
  public String toString() {
    return "CommonResponseReason{" +
      "response_code=" + response_code +
      ", reason_code=" + reason_code +
      ", error_description='" + error_description + '\'' +
      ", pkid=" + pkid +
      '}';
  }
}
