
package com.example.asus.yaratube.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("homeitem")
    @Expose
    private List<Homeitem> homeitem = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tabStrip")
    @Expose
    private List<Object> tabStrip = null;
    @SerializedName("headeritem")
    @Expose
    private List<Product> headeritem = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("parent_categories")
    @Expose
    private List<ParentCategory> parentCategories = null;

    public List<Homeitem> getHomeitem() {
        return homeitem;
    }

    public void setHomeitem(List<Homeitem> homeitem) {
        this.homeitem = homeitem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getTabStrip() {
        return tabStrip;
    }

    public void setTabStrip(List<Object> tabStrip) {
        this.tabStrip = tabStrip;
    }

    public List<Product> getHeaderitem() {
        return headeritem;
    }

    public void setHeaderitem(List<Product> headeritem) {
        this.headeritem = headeritem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<ParentCategory> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<ParentCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

}
