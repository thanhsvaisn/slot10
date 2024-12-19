package org.example.javastringboots.mapper;

import org.example.javastringboots.dto.req.ReviewReqDTO;
import org.example.javastringboots.dto.res.ReviewResDTO;
import org.example.javastringboots.entity.Reviews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Reviews toEntity(ReviewReqDTO reviewReqDTO);
    ReviewResDTO toDTO(Reviews review);
}
