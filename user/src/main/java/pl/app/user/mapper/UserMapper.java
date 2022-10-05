package pl.app.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.app.user.domain.User;
import pl.app.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)//ignorujemy pole
    UserDto toDto(User user);
}
