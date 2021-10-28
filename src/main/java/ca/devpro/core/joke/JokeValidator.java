package ca.devpro.core.joke;

import ca.devpro.api.JokeDto;
import ca.devpro.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class JokeValidator {

  static final String SETUP_REQUIRED = "SETUP_REQUIRED";
  static final String PUNCHLINE_REQUIRED = "PUNCHLINE_REQUIRED";

  public void validateAndThrow(JokeDto dto) {
    Map<String, String> errors = validate(dto);
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  public Map<String, String> validate(JokeDto dto) {
    Map<String, String> errors = new LinkedHashMap<>();
    validateSetup(errors, dto);
    validatePunchline(errors, dto);
    return errors;
  }

  private void validateSetup(Map<String, String> errors, JokeDto dto) {
    if (StringUtils.isBlank(dto.getSetup())) {
      errors.put("setup", SETUP_REQUIRED);
    }
  }

  private void validatePunchline(Map<String, String> errors, JokeDto dto) {
    if (StringUtils.isBlank(dto.getPunchline())) {
      errors.put("punchline", PUNCHLINE_REQUIRED);
    }
  }
}
