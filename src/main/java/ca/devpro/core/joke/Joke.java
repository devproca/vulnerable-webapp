package ca.devpro.core.joke;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "joke")
@Getter
@Setter
@Accessors(chain = true)
public class Joke {

  @Id
  @Column(name = "joke_id")
  @Type(type = "uuid-char")
  @Setter(AccessLevel.NONE)
  private UUID jokeId;

  @Column(name = "setup")
  private String setup;

  @Column(name = "punchline")
  private String punchline;

  public static Joke newInstance() {
    Joke joke = new Joke();
    return joke;
  }
}
