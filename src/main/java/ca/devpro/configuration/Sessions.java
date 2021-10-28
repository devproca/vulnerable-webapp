package ca.devpro.configuration;

import ca.devpro.api.UserDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Sessions {
  private final Map<String, UserDto> sessions = new ConcurrentHashMap<>();

  public String add(UserDto user) {
    String sessionId = RandomStringUtils.random(256, 0, 0, true, true, null, new SecureRandom());
    sessions.put(sessionId, user);
    return sessionId;
  }

  public void remove(String sessionId) {
    sessions.remove(sessionId);
  }

  public boolean hasSession(String sessionId) {
    return getUserBySessionId(sessionId).isPresent();
  }

  public Optional<UserDto> getUserBySessionId(String sessionId) {
    return Optional.ofNullable(sessions.get(sessionId));
  }

}
