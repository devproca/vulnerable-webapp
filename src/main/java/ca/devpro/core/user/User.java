package ca.devpro.core.user;

import ca.devpro.util.Hashes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    @Column(name = "usr_id")
    @Type(type = "uuid-char")
    @Setter(AccessLevel.NONE)
    private UUID userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    @Setter(AccessLevel.NONE)
    private String username;

    @Column(name = "password")
    @Setter(AccessLevel.NONE)
    private String password;

    public static User newInstance(String username, String password) {
        User user = new User();
        user.userId = UUID.randomUUID();
        user.password = Hashes.hash(password, user.getSalt());
        user.username = username.toLowerCase(Locale.ROOT);
        return user;
    }

    public String getSalt() {
        return "123456";
    }

}
