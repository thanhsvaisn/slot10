package org.example.javastringboots.service;

import org.example.javastringboots.dto.req.OrderReqDTO;
import org.example.javastringboots.dto.res.OrderResDTO;
import org.example.javastringboots.entity.Category;
import org.example.javastringboots.entity.Order;
import org.example.javastringboots.entity.OrderItem;
import org.example.javastringboots.entity.Product;
import org.example.javastringboots.mapper.OrderMapper;
import org.example.javastringboots.repository.CategoryRepository;
import org.example.javastringboots.repository.OrderItemRepository;
import org.example.javastringboots.repository.OrderReposetory;
import org.example.javastringboots.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderService {
    private final OrderReposetory orderReposetory;

    private final OrderMapper orderMapper;

    private final ProductRepository productRepository;

    private final OrderItemRepository orderItemRepository;

    private final CategoryRepository categoryRepository;

    public OrderService(OrderReposetory orderReposetory, OrderMapper orderMapper, ProductRepository productRepository, OrderItemRepository orderItemRepository, CategoryRepository categoryRepository) {
        this.orderReposetory = orderReposetory;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public OrderResDTO createOrder(OrderReqDTO orderReqDTO){
        Order order = new Order();
        order.setGrandTotal(0.0);

        List<OrderItem> items = orderReqDTO.getItems().stream().map(
                item-> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(item.getProductId());
                    orderItem.setQty(item.getQty());
                    Product p = productRepository.findById(item.getProductId()).orElseThrow(
                            ()-> new RuntimeException("Product not found: "+item.getProductId())
                    );
                    if(p.getQty() < item.getQty())
                        throw new RuntimeException("Insufficient stock for product: "+p.getName());
                    p.setQty(p.getQty() - item.getQty());
                    productRepository.save(p);
                    orderItem.setPrice(p.getPrice());
                    orderItem.setOrder(order);
                    order.setGrandTotal(order.getGrandTotal() + orderItem.getQty() * p.getPrice());
                    return orderItem;
                }
        ).toList();
        order.setItems(items);
        order.setCreatedAt(new Date());
        order.setShippingAdress(orderReqDTO.getShippingAddress());
        order.setTelephone(orderReqDTO.getTelephone());

        Order savedOrder = orderReposetory.save(order);
        return orderMapper.toDTO(savedOrder);

    }
    @Transactional
    public OrderResDTO updateOrderStatus(Long orderId, int status) {
        Order order = orderReposetory.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found with ID: " + orderId)
        );

        // Validate status
        if (status < 0 || status > 3) { // Assuming 0: Pending, 1: Confirmed, 2: Shipped, 3: Delivered
            throw new RuntimeException("Invalid status value: " + status);
        }

        order.setStatus(status);
        Order updatedOrder = orderReposetory.save(order);
        return orderMapper.toDTO(updatedOrder);
    }
    @Transactional
    public OrderResDTO cancelOrder(Long orderId, String cancelReason) {
        Order order = orderReposetory.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order not found with ID: " + orderId)
        );

        // Rollback product quantities
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProductId()).orElseThrow(
                    () -> new RuntimeException("Product not found: " + item.getProductId())
            );
            product.setQty(product.getQty() + item.getQty());
            productRepository.save(product);
        }

        // Update order status and reason
        order.setStatus(-1); // Assuming -1 represents "Cancelled"
        order.setGrandTotal(0.0); // Optionally set total to 0 if refund is processed
        order.setCancelReason("Cancelled: " + cancelReason); // Optionally use a separate field for the reason
        Order cancelledOrder = orderReposetory.save(order);

        return orderMapper.toDTO(cancelledOrder);
    }

    public double calculateTotalRevenue() {
        List<Order> orders = orderReposetory.findAll();
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue += order.getGrandTotal();
        }
        return totalRevenue;
    }
    public Product findBestSellingProduct() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        Map<Long, Integer> productSales = new HashMap<>();

        for (OrderItem orderItem : orderItems) {
            productSales.put(orderItem.getProductId(),
                    (int) (productSales.getOrDefault(orderItem.getProductId(), 0) + orderItem.getQty()));
        }

        Long bestSellingProductId = Collections.max(productSales.entrySet(), Map.Entry.comparingByValue()).getKey();
        return productRepository.findById(bestSellingProductId).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

    public Category findHighestRevenueCategory() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        Map<Long, Double> categoryRevenue = new HashMap<>();

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            double revenue = orderItem.getQty() * product.getPrice();
            categoryRevenue.put(product.getCategory().getId(),
                    categoryRevenue.getOrDefault(product.getCategory().getId(), 0.0) + revenue);
        }

        Long highestRevenueCategoryId = Collections.max(categoryRevenue.entrySet(), Map.Entry.comparingByValue()).getKey();
        return categoryRepository.findById(highestRevenueCategoryId).orElseThrow(
                () -> new RuntimeException("Category not found"));
    }



}
