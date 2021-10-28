package ca.devpro.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BasicCredentialsDto {

    private String username;
    private String password;
}
