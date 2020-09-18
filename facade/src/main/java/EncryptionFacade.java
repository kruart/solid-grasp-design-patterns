import java.security.NoSuchAlgorithmException;

public class EncryptionFacade {
    public String encrypt(String hashType, String msg) throws NoSuchAlgorithmException {
        return switch (hashType) {
            case "MD5" -> new MD5().encrypt(msg);
            case "SHA" -> new SHA().encrypt(msg);
            case "SHA256" -> new SHA256().encrypt(msg);
            default -> throw new NoSuchAlgorithmException();
        };
    }
}
