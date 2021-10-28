package ca.devpro.controller;

import ca.devpro.api.UserDto;
import ca.devpro.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

  @Autowired
  private UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto register(@RequestBody UserDto dto) {
    return userService.create(dto);
  }

  @GetMapping()
  public List<UserDto> findAll() {
    return userService.findAll();
  }

  //DO I WANt these?
  @GetMapping("/{userId}")
  public UserDto get(@PathVariable("userId") UUID userId) {
    return userService.get(userId);
  }

  @PutMapping("/{userId}")
  public UserDto update(@PathVariable("userId") UUID userId, @RequestBody UserDto dto) {
    dto.setUserId(userId);
    return userService.update(dto);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("userId") UUID userId) {
    userService.delete(userId);
  }
}
