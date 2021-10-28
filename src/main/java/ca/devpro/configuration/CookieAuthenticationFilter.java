package ca.devpro.configuration;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
public class CookieAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

  private final Sessions sessions;
  private final AuthenticationManager authenticationManager;

  @PostConstruct
  public void init() {
    setAuthenticationManager(authenticationManager);
    setContinueFilterChainOnUnsuccessfulAuthentication(false);
  }

  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    String sessionId = Optional.of(request)
      .map(r -> WebUtils.getCookie(r, "ssotoken"))
      .map(Cookie::getValue)
      .orElse(null);

    if (sessionId == null) {
      return null;
    } else if (!sessions.hasSession(sessionId)) {
      return null;
    }
    return sessions.getUserBySessionId(sessionId).get();
  }

  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }
}
