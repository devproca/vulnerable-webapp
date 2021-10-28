package ca.devpro.controller;

import ca.devpro.api.BasicCredentialsDto;
import ca.devpro.core.user.UserService;
import ca.devpro.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

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
}
