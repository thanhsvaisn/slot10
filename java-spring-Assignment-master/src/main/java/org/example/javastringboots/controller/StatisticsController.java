package org.example.javastringboots.controller;

import org.example.javastringboots.entity.Category;
import org.example.javastringboots.entity.Product;
import org.example.javastringboots.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {
    private final OrderService orderService;

    public StatisticsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<Double> getTotalRevenue() {
        double totalRevenue = orderService.calculateTotalRevenue();
        return ResponseEntity.ok(totalRevenue);
    }

    @GetMapping("/best-selling-product")
    public ResponseEntity<Product> getBestSellingProduct() {
        Product bestSellingProduct = orderService.findBestSellingProduct();
        return ResponseEntity.ok(bestSellingProduct);
    }


}
