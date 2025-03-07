package com.MT.MDCWithMultiThreading.mapper;

import com.MT.MDCWithMultiThreading.Entity.User;
import com.MT.MDCWithMultiThreading.dto.UserDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Usermapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    User toUser(UserDto userDto, @MappingTarget User user);

    UserDto toUserDto(User user);
}
