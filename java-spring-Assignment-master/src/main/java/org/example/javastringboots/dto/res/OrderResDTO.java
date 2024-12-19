package org.example.javastringboots.dto.res;

import org.example.javastringboots.dto.req.OrderItemReqDTO;
import org.example.javastringboots.dto.req.ProductRequestDTO;

import java.util.Date;
import java.util.List;

public class OrderResDTO {
    private Long id;
    private Double grandTotal;
    private Date createdAt;
    private int status;
    private String shippingAddress;
    private String telephone;
    private List<OrderItemReqDTO> items;
    private List<ProductRequestDTO> products;

    private String cancelReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<OrderItemReqDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemReqDTO> items) {
        this.items = items;
    }

    public List<ProductRequestDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequestDTO> products) {
        this.products = products;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
}
