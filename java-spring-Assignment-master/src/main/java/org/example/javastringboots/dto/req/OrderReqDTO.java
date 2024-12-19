package org.example.javastringboots.dto.req;

import java.util.List;

public class OrderReqDTO {
    private List<OrderItemReqDTO> items;

    private String shippingAddress;
    private String telephone;

    public List<OrderItemReqDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemReqDTO> items) {
        this.items = items;
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
}
