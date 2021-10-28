package ca.devpro.controller;

import ca.devpro.api.JokeDto;
import ca.devpro.core.joke.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

  @Autowired
  private JokeService jokeService;

  @GetMapping()
  public List<JokeDto> findAll() {
    return jokeService.findAll();
  }

  @GetMapping("/{jokeId}")
  public JokeDto get(@PathVariable("jokeId") UUID jokeId) {
    return jokeService.get(jokeId);
  }

  @PutMapping("/{jokeId}")
  public JokeDto update(@PathVariable("jokeId") UUID jokeId, @RequestBody JokeDto dto) {
    dto.setJokeId(jokeId);
    return jokeService.update(dto);
  }

  @DeleteMapping("/{jokeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("jokeId") UUID jokeId) {
    jokeService.delete(jokeId);
  }
}
