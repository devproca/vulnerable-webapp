package ca.devpro.controller;

import ca.devpro.api.BasicCredentialsDto;
import ca.devpro.api.UserDto;
import ca.devpro.core.user.UserService;
import ca.devpro.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody BasicCredentialsDto credentials) {
        return userService.login(credentials.getUsername(), credentials.getPassword())
                .map(t -> {
                    ResponseCookie cookie = ResponseCookie
                            .from("ssotoken", t.getToken())
                            .path("/")
                            .build();

                    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
                })
                .orElseThrow(UnauthorizedException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @GetMapping()
    public List<UserDto> findAll() {
        return userService.findAll();
    }

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
