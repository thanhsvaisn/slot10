package org.example.javastringboots.service;

import org.example.javastringboots.dto.req.ReviewReqDTO;
import org.example.javastringboots.dto.res.ReviewResDTO;
import org.example.javastringboots.entity.Order;
import org.example.javastringboots.entity.Reviews;
import org.example.javastringboots.mapper.ReviewMapper;
import org.example.javastringboots.repository.OrderReposetory;
import org.example.javastringboots.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final OrderReposetory orderReposetory;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, OrderReposetory orderReposetory, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.orderReposetory = orderReposetory;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    public ReviewResDTO addReview(ReviewReqDTO reviewReqDTO) {
        Order order = orderReposetory.findById(reviewReqDTO.getOrderId()).orElseThrow(
                () -> new RuntimeException("Order not found: " + reviewReqDTO.getOrderId())
        );

        if (order.getStatus() != 2) { // Assuming `2` means "Completed"
            throw new RuntimeException("Cannot review an order that is not completed.");
        }

        Reviews review = reviewMapper.toEntity(reviewReqDTO);
        review.setOrder(order);

        Reviews savedReview = reviewRepository.save(review);
        return reviewMapper.toDTO(savedReview);
    }
}
