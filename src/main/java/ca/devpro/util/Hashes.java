package ca.devpro.util;

import lombok.experimental.UtilityClass;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@UtilityClass
public class Hashes {

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final SecretKeyFactory factory;

    static {
        try {
            factory = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void main(String[] args) {
        System.err.println(hash("Today123", "d2e8db5a-fc3e-476a-9b77-77bd997044c3"));
    }

    public static String hash(String password, String salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        try {
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
