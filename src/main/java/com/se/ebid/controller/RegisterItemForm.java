/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se.ebid.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mtmmoei
 */
public class RegisterItemForm {
    @Size(min = 1, max = 80, message = "Title must not be empty or longer than 80 characters")
    private String title;
    
    //Size(min = 1, message = "Specifics must not be empty")
    private String specifics;
    
    @Size(min = 1, message = "Detail must not be empty")
    private String detail;
    
    private CategoryType category;
    private MultipartFile[] photos;
    private SellingType sellingType;
    
   // @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    //@Pattern(regexp = "[0-9]+.[0-9]{2}")
   // @Size(min = 1, message = "Price must not be empty")
    //@Digits(integer = 20, fraction = 2, message = "Price is invalid")
    private double price;
    
    @Min(value = 1, message = "Quantity must be at least 1")
    private long quantity;
    
    private Timestamp startTime;
    
    @Future(message = "End time is invalid")
    private Date endTime;
//    private String paymentMethod;
    
    @Size(min = 1, message = "Shipping service must not be empty")
    private String shippingService;
    
    @Size(min = 1, message = "Shipping cost must not be empty")
    private String shippingCost;
    
    @Size(min = 1, message = "Package detail must not be empty")
    private String packageDetail;
    
    @Size(min = 1, message = "Return policy must not be empty")
    private String returnPolicy;
//    private String delivery;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecifics() {
        return specifics;
    }

    public void setSpecifics(String specifics) {
        this.specifics = specifics;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }

    public SellingType getSellingType() {
        return sellingType;
    }
    
    @AssertTrue(message = "Selling method must be selected")
    private boolean isValid() {
        return this.sellingType != null;
    }
    
    @AssertTrue(message = "Price must be higher than 0")
    private boolean isValid2() {
        return this.price > 0;
    }
    
    @AssertTrue(message = "The difference between end time and current time must be at least 3 minutes")
    private boolean isValid3() {
        return this.endTime.getTime() - System.currentTimeMillis() > 1000*60*3;
    }

    public void setSellingType(SellingType sellingType) {
        this.sellingType = sellingType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

//    public String getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(String paymentMethod) {
//        this.paymentMethod = paymentMethod;
//    }

    public String getShippingService() {
        return shippingService;
    }

    public void setShippingService(String shippingService) {
        this.shippingService = shippingService;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(String packageDetail) {
        this.packageDetail = packageDetail;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }
    
}
