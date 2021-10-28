package ca.devpro.controller;

import ca.devpro.api.BasicCredentialsDto;
import ca.devpro.api.UserDto;
import ca.devpro.configuration.CustomSecurityContextHolder;
import ca.devpro.configuration.Sessions;
import ca.devpro.core.user.UserService;
import ca.devpro.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

  @Autowired
  private UserService userService;
  @Autowired
  private Sessions sessions;

  @PostMapping("/login")
  public ResponseEntity<UserDto> login(@RequestBody BasicCredentialsDto credentials) {
    return userService.login(credentials.getUsername(), credentials.getPassword())
      .map(t -> {
        ResponseCookie cookie = ResponseCookie
          .from("ssotoken", t.getToken())
          .path("/")
          .build();

        UserDto user = sessions.getUserBySessionId(t.getToken()).orElseThrow(IllegalArgumentException::new);

        return ResponseEntity.ok()
          .header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(user);
      })
      .orElseThrow(UnauthorizedException::new);
  }

  @GetMapping("/currentUser")
  public UserDto getCurrentUser() {
    return CustomSecurityContextHolder.getCurrentUser();
  }
}
