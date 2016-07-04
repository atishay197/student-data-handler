package service.EncryptionDecryption;

/**
 * Created by atishay197 on 6/28/16.
 */
public class EncryptDecrypt {

    private static String SecretKey = "atishay";

    public static String encrypt(String roll)
    {
        String encryptedRoll = SecretKey.concat(roll);
        return encryptedRoll;
    }

    public static int decrypt(String encryptedRoll)
    {
        int keylen = SecretKey.length();
        String roll = encryptedRoll.substring(keylen);
        return Integer.parseInt(roll);
    }
}
