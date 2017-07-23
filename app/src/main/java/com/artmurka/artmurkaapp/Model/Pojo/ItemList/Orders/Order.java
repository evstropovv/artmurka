
package com.artmurka.artmurkaapp.Model.Pojo.ItemList.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("add_date")
    @Expose
    private String addDate;
    @SerializedName("delivery")
    @Expose
    private Delivery delivery;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("payment")
    @Expose
    private Payment payment;
    @SerializedName("rem")
    @Expose
    private String rem;
    @SerializedName("currency")
    @Expose
    private Currency currency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mcml")
    @Expose
    private String mcml;
    @SerializedName("order_hash")
    @Expose
    private String orderHash;
    @SerializedName("m1c")
    @Expose
    private String m1c;
    @SerializedName("hide")
    @Expose
    private String hide;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("discount")
    @Expose
    private Discount discount;
    @SerializedName("akey")
    @Expose
    private String akey;
    @SerializedName("fields")
    @Expose
    private Fields fields;
    @SerializedName("cnt")
    @Expose
    private String cnt;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("mod_date")
    @Expose
    private String modDate;
    @SerializedName("profit")
    @Expose
    private String profit;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("referer")
    @Expose
    private String referer;
    @SerializedName("refhost")
    @Expose
    private String refhost;

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMcml() {
        return mcml;
    }

    public void setMcml(String mcml) {
        this.mcml = mcml;
    }

    public String getOrderHash() {
        return orderHash;
    }

    public void setOrderHash(String orderHash) {
        this.orderHash = orderHash;
    }

    public String getM1c() {
        return m1c;
    }

    public void setM1c(String m1c) {
        this.m1c = m1c;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getAkey() {
        return akey;
    }

    public void setAkey(String akey) {
        this.akey = akey;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getRefhost() {
        return refhost;
    }

    public void setRefhost(String refhost) {
        this.refhost = refhost;
    }

}
