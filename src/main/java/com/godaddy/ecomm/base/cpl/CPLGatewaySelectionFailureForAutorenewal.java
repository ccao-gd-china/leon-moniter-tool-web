package com.godaddy.ecomm.base.cpl;

/**
 * this class corresponding to the database table.
 */
public class CPLGatewaySelectionFailureForAutorenewal {

    private String date_entered;
    private String shopper_id;
    private String order_id;
    private String payment;
    private String payment_type;
    private String billing_country_code;
    private String transactionCurrencyType;
    private String gateway_raw = "";
    private int snapshot_Id;
    private int reason_code;
    private int response_code;

    public CPLGatewaySelectionFailureForAutorenewal() {
    }

    public CPLGatewaySelectionFailureForAutorenewal(String date_entered, String shopper_id, String order_id, String payment, String payment_type, String billing_country_code, String transactionCurrencyType, String gateway_raw, int snapshot_Id, int reason_code, int response_code) {
        this.date_entered = date_entered;
        this.shopper_id = shopper_id;
        this.order_id = order_id;
        this.payment = payment;
        this.payment_type = payment_type;
        this.billing_country_code = billing_country_code;
        this.transactionCurrencyType = transactionCurrencyType;
        this.gateway_raw = gateway_raw;
        this.snapshot_Id = snapshot_Id;
        this.reason_code = reason_code;
        this.response_code = response_code;
    }

    public String getDate_entered() {
        return date_entered;
    }

    public void setDate_entered(String date_entered) {
        this.date_entered = date_entered;
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

    public String getTransactionCurrencyType() {
        return transactionCurrencyType;
    }

    public void setTransactionCurrencyType(String transactionCurrencyType) {
        this.transactionCurrencyType = transactionCurrencyType;
    }

    public String getGateway_raw() {
        return gateway_raw;
    }

    public void setGateway_raw(String gateway_raw) {
        this.gateway_raw = gateway_raw;
    }

    public int getSnapshot_Id() {
        return snapshot_Id;
    }

    public void setSnapshot_Id(int snapshot_Id) {
        this.snapshot_Id = snapshot_Id;
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

    @Override
    public String toString() {
        return "CPLGatewaySelectionFailureForAutorenewal{" +
                "date_entered='" + date_entered + '\'' +
                ", shopper_id='" + shopper_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", payment='" + payment + '\'' +
                ", payment_type='" + payment_type + '\'' +
                ", billing_country_code='" + billing_country_code + '\'' +
                ", transactionCurrencyType='" + transactionCurrencyType + '\'' +
                ", gateway_raw='" + gateway_raw + '\'' +
                ", snapshot_Id=" + snapshot_Id +
                ", reason_code=" + reason_code +
                ", response_code=" + response_code +
                '}';
    }
}
