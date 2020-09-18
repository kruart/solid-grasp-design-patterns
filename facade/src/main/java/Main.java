import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String myText = "Encrypt this text";

        EncryptionFacade encryptionSystem = new EncryptionFacade();
        System.out.println("MD5 encryption");
        System.out.println(encryptionSystem.encrypt("MD5", myText));

        System.out.println("SHA encryption");
        System.out.println(encryptionSystem.encrypt("SHA", myText));

        System.out.println("SHA256 encryption");
        System.out.println(encryptionSystem.encrypt("SHA256", myText));
    }
}
