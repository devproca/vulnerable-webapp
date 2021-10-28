package ca.devpro.core.joke;

import ca.devpro.api.JokeDto;
import org.springframework.stereotype.Component;

@Component
public class JokeAssembler {

  public JokeDto assemble(Joke entity) {
    return new JokeDto()
      .setJokeId(entity.getJokeId())
      .setSetup(entity.getSetup())
      .setPunchline(entity.getPunchline());
  }

  public Joke disassemble(JokeDto dto) {
    Joke entity = Joke.newInstance();
    return disassembleInto(dto, entity);
  }

  public Joke disassembleInto(JokeDto dto, Joke entity) {
    return entity.setSetup(dto.getSetup())
      .setPunchline(dto.getPunchline());
  }
}
