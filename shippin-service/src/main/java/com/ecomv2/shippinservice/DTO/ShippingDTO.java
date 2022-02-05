package com.ecomv2.shippinservice.DTO;

public class ShippingDTO {

    private Long id;
    private String address;
    private String email;
    private String contact;
    private Long userId;
    private Long orderId;
    private Double shippingAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }
    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }
}
