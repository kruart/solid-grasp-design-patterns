import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public String encrypt(String msg) {
        String hash = "";
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(msg.getBytes());

            byte[] textBytes = msgDigest.digest();

            StringBuilder buffer = new StringBuilder();
            for (byte textByte : textBytes) {
                buffer.append(Integer.toString((textByte & 0xff) + 0x100, 16).substring(1));
            }

            hash = buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
}
