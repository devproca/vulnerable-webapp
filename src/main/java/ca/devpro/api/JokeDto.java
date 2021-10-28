package ca.devpro.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class JokeDto {
  private UUID jokeId;
  private String setup;
  private String punchline;
}
