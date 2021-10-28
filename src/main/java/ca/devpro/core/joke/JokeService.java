package ca.devpro.core.joke;

import ca.devpro.api.JokeDto;
import ca.devpro.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JokeService {

  @Autowired
  private JokeAssembler jokeAssembler;
  @Autowired
  private JokeRepository jokeRepository;
  @Autowired
  private JokeValidator jokeValidator;


  public JokeDto create(JokeDto dto) {
    jokeValidator.validateAndThrow(dto);
    Joke entity = jokeAssembler.disassemble(dto);
    jokeRepository.save(entity);
    return jokeAssembler.assemble(entity);
  }

  public List<JokeDto> findAll() {
    return jokeRepository.findAll()
      .stream()
      .map(jokeAssembler::assemble)
      .collect(Collectors.toList());
  }

  public JokeDto get(UUID jokeId) {
    return jokeRepository.findById(jokeId)
      .map(jokeAssembler::assemble)
      .orElseThrow(NotFoundException::new);
  }

  public JokeDto update(JokeDto dto) {
    jokeValidator.validateAndThrow(dto);
    return jokeRepository.findById(dto.getJokeId())
      .map(entity -> jokeAssembler.disassembleInto(dto, entity))
      .map(jokeRepository::save)
      .map(jokeAssembler::assemble)
      .orElseThrow(() -> new NotFoundException());
  }

  public void delete(UUID jokeId) {
    jokeRepository.findById(jokeId).ifPresentOrElse(jokeRepository::delete, () -> {
      throw new NotFoundException();
    });
  }
}
