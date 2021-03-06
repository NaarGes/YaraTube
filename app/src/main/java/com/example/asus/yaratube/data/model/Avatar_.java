
package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Avatar_ {

    @SerializedName("mdpi")
    @Expose
    private String mdpi;
    @SerializedName("xxxdpi")
    @Expose
    private String xxxdpi;
    @SerializedName("hdpi")
    @Expose
    private String hdpi;
    @SerializedName("xxhdpi")
    @Expose
    private String xxhdpi;
    @SerializedName("xhdpi")
    @Expose
    private String xhdpi;

    @ParcelConstructor
    Avatar_() {

    }

    public String getMdpi() {
        return mdpi;
    }

    public void setMdpi(String mdpi) {
        this.mdpi = mdpi;
    }

    public String getXxxdpi() {
        return xxxdpi;
    }

    public void setXxxdpi(String xxxdpi) {
        this.xxxdpi = xxxdpi;
    }

    public String getHdpi() {
        return hdpi;
    }

    public void setHdpi(String hdpi) {
        this.hdpi = hdpi;
    }

    public String getXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(String xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public String getXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(String xhdpi) {
        this.xhdpi = xhdpi;
    }

}
