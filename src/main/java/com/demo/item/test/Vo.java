package com.demo.item.test;

import com.google.gson.annotations.SerializedName;

public class Vo {


    /**
     * mchId : 0
     * orderId : BOSS155261894027600013937
     * appid : wxa4eab8e7a92a1ce5
     * partnerid : 1491909032
     * prepayid : wx1511022438389915d38d41583978775286
     * noncestr : TAjYb0POHkKbrxTi
     * timestamp : 1552618944
     * sign : 0175478C3A562E7A9805BE9F54951193
     * package : Sign=WXPay
     */

    private String mchId;
    private String orderId;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;
    private String sign;
    @SerializedName("package")
    private String packageX;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }
}
