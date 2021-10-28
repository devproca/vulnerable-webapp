package ca.devpro.core.user;

import ca.devpro.api.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

  public UserDto assemble(User entity) {
    return new UserDto()
      .setUserId(entity.getUserId())
      .setFirstName(entity.getFirstName())
      .setLastName(entity.getLastName())
      .setUsername(entity.getUsername());
  }

  public User disassemble(UserDto dto) {
    User entity = User.newInstance(dto.getUsername(), dto.getPassword());
    return disassembleInto(dto, entity);
  }

  public User disassembleInto(UserDto dto, User entity) {
    return entity.setLastName(dto.getLastName())
      .setFirstName(dto.getFirstName());
  }
}
