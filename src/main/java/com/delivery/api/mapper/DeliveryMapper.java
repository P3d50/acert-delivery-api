package com.delivery.api.mapper;

import com.delivery.api.dto.request.DeliveryDTO;
import com.delivery.api.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    Delivery toModel(DeliveryDTO deliveryDTO);

    DeliveryDTO toDTO(Delivery delivery);
}
