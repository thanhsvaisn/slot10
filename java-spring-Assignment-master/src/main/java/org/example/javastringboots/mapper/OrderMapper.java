package org.example.javastringboots.mapper;

import org.example.javastringboots.dto.req.OrderReqDTO;
import org.example.javastringboots.dto.res.OrderResDTO;
import org.example.javastringboots.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderReqDTO orderReqDTO);
    OrderResDTO toDTO(Order order);
}
