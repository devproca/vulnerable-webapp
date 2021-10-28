package ca.devpro.configuration;

import ca.devpro.api.UserDto;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CustomSecurityContextHolder {

  public static UserDto getCurrentUser() {
    return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
      .getUser();
  }
}
