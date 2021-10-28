package ca.devpro.configuration;

import ca.devpro.api.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class CustomAuthenticationUserDetailsService implements AuthenticationUserDetailsService {

  @Override
  public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
    return new CustomUserDetails((UserDto) token.getPrincipal());
  }
}
