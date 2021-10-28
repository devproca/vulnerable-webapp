package ca.devpro.core.user;

import ca.devpro.api.AuthenticationTokenDto;
import ca.devpro.api.UserDto;
import ca.devpro.configuration.Sessions;
import ca.devpro.exception.NotFoundException;
import ca.devpro.util.Hashes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  private UserAssembler userAssembler;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserValidator userValidator;
  @Autowired
  private Sessions sessions;

  public Optional<AuthenticationTokenDto> login(String username, String password) {

    return userRepository.findByUsername(username.toLowerCase(Locale.ROOT))
      .filter(u -> u.getPassword().equals(Hashes.hash(password, u.getSalt())))
      .map(userAssembler::assemble)
      .map(dto -> new AuthenticationTokenDto().setToken(sessions.add(dto)));
  }

  public UserDto create(UserDto dto) {
    userValidator.validateAndThrow(dto);
    User entity = userAssembler.disassemble(dto);
    userRepository.save(entity);
    return userAssembler.assemble(entity);
  }

  public List<UserDto> findAll() {
    return userRepository.findAll()
      .stream()
      .map(userAssembler::assemble)
      .collect(Collectors.toList());
  }

  public UserDto get(UUID userId) {
    return userRepository.findById(userId)
      .map(userAssembler::assemble)
      .orElseThrow(() -> new NotFoundException());
  }

  public UserDto update(UserDto dto) {
    userValidator.validateAndThrow(dto);
    return userRepository.findById(dto.getUserId())
      .map(entity -> userAssembler.disassembleInto(dto, entity))
      .map(userRepository::save)
      .map(userAssembler::assemble)
      .orElseThrow(() -> new NotFoundException());
  }

  public void delete(UUID userId) {
    userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
      throw new NotFoundException();
    });
  }
}


