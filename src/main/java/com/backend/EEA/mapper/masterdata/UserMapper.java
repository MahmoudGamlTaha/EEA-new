package com.backend.EEA.mapper.masterdata;

import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.masterdata.UserRoleDto;
import com.backend.EEA.model.entity.masterdata.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses={CompanyMapper.class, DepartmentMapper.class, UserRoleMapper.class})
public interface UserMapper {

    User toUser(UserDto userDto);
    @Mapping( target = "password", expression = "java(null)")
    UserDto toUserDto(User user);

}
