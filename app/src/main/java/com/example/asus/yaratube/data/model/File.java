
package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class File {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("file")
    @Expose
    private String file;
    /*@SerializedName("img")
    @Expose
    private Object img;*/
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("sku_registered")
    @Expose
    private Boolean skuRegistered;
    @SerializedName("sku_reg_date")
    @Expose
    private String skuRegDate;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("is_purchased")
    @Expose
    private Boolean isPurchased;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("description")
    @Expose
    private String description;
    /*@SerializedName("sub_fa")
    @Expose
    private Object subFa;
    @SerializedName("sub_en")
    @Expose
    private Object subEn;*/
    @SerializedName("is_downloadable")
    @Expose
    private Boolean isDownloadable;
    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("is_enable")
    @Expose
    private Boolean isEnable;
    @SerializedName("file_type")
    @Expose
    private Integer fileType;
    @SerializedName("file_redirect")
    @Expose
    private String fileRedirect;

    @ParcelConstructor
    File() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
/*

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }
*/

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getSkuRegistered() {
        return skuRegistered;
    }

    public void setSkuRegistered(Boolean skuRegistered) {
        this.skuRegistered = skuRegistered;
    }

    public String getSkuRegDate() {
        return skuRegDate;
    }

    public void setSkuRegDate(String skuRegDate) {
        this.skuRegDate = skuRegDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Object getSubFa() {
        return subFa;
    }

    public void setSubFa(Object subFa) {
        this.subFa = subFa;
    }

    public Object getSubEn() {
        return subEn;
    }

    public void setSubEn(Object subEn) {
        this.subEn = subEn;
    }
*/
    public Boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setIsDownloadable(Boolean isDownloadable) {
        this.isDownloadable = isDownloadable;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileRedirect() {
        return fileRedirect;
    }

    public void setFileRedirect(String fileRedirect) {
        this.fileRedirect = fileRedirect;
    }

}
