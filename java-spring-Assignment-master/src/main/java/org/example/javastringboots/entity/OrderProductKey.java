package org.example.javastringboots.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderProductKey implements Serializable {
    private Long orderId;
    private Long ProductId;

    public OrderProductKey() {
    }

    public OrderProductKey(Long orderId, Long productId) {
        this.orderId = orderId;
        ProductId = productId;
    }

    //cach lam k can khoa chinh id trong bang trung gian.
}
