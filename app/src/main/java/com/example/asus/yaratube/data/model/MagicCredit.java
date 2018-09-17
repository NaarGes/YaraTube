package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MagicCredit {

    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("cash")
    @Expose
    private Integer cash;
    @SerializedName("gem")
    @Expose
    private Integer gem;
    @SerializedName("coin")
    @Expose
    private Integer coin;

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getGem() {
        return gem;
    }

    public void setGem(Integer gem) {
        this.gem = gem;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

}