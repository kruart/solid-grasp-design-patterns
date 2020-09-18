# Facade
`Provides a simplified interface 
to a library, a framework, or any other complex set of classes.`

Example:  

Imagine that you have the old project that uses 3 type of hash functions SHA, SHA256, MD5.  
Let's omit the fact that they could use a common interface, 
your old project is pretty complex and any changes in hierarchy could break something,
or there's 3 different libraries that provide those functionality, etc.

  
MD5
```java
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
``` 

SHA
```java
public class SHA {
    public String encrypt(String msg) {
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
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
```

SHA256
```java
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
```

 So instead of use all hash algorithms separately, 
 developer would prefer to simplify code and hide the classes 
 by provided a Facade class taking care of the function calls.
 
 ```java
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
``` 

Now we only need to create `EncryptionFacade` class
 and we can work with all algorithms through this class:
```java
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
```
 
 Output:
```
MD5 encryption
8608b4eae12d595dce48b6857132d5d0
SHA encryption
68e7b34151cfdd2cd26ffbd0c65aabd8efcf4160
SHA256 encryption
3b41d4bc4fcec64fb0924e9080495520938b17443b11ef41750784872c0d4e8c
```