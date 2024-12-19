package org.example.javastringboots.dto.req;

public class OrderItemReqDTO {
    private Long productId;

    private Long qty;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
