package com.delivery.api.mapper;


import com.delivery.api.dto.request.AppUserDTO;
import com.delivery.api.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    AppUser toModel(AppUserDTO userDTO);

    AppUserDTO toDTO(AppUser user);
}
