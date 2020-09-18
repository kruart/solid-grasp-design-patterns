import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public String encrypt(String msg) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] textBytes = digest.digest(msg.getBytes(StandardCharsets.UTF_8));

            StringBuilder buffer = new StringBuilder();
            for (byte textByte : textBytes) {
                buffer.append(Integer.toString((textByte & 0xff) + 0x100, 16).substring(1));
            }
            hash = buffer.toString();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
