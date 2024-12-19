package org.example.javastringboots.controller;

import org.example.javastringboots.dto.req.OrderReqDTO;
import org.example.javastringboots.dto.res.OrderResDTO;
import org.example.javastringboots.entity.Order;
import org.example.javastringboots.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/checkout")
public class CheckoutControlller {
    private final OrderService orderService;

    public CheckoutControlller(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderResDTO> checkout(@RequestBody OrderReqDTO order){
        return ResponseEntity.ok(orderService.createOrder(order));
    }
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResDTO> cancelOrder(
            @PathVariable Long orderId,
            @RequestBody String cancelReason
    ) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId, cancelReason));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam int status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }



}
