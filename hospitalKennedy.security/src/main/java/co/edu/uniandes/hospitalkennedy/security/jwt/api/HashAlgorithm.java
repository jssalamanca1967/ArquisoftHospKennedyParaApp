package co.edu.uniandes.hospitalkennedy.security.jwt.api;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by oscarkiyoshigegarcesaparicio on 17/09/14.
 */

/**
 * This class is used to get the Hash firmed of a String (Value) with an explicit Key.
 */
public class HashAlgorithm {

    public static byte[] getHash(String key, String value, JwtHashAlgorithm hash){

        byte[] returnHash = null;
        SecretKeySpec secret_key;

        try {

            switch (hash) {
                case HS256:
                    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                    secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
                    sha256_HMAC.init(secret_key);
                    returnHash = sha256_HMAC.doFinal(value.getBytes());
                    break;
                case HS384:
                    Mac sha384_HMAC = Mac.getInstance("HmacSHA384");
                    secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA384");
                    sha384_HMAC.init(secret_key);
                    returnHash = sha384_HMAC.doFinal(value.getBytes());
                    break;
                case HS512:
                    Mac sha512_HMAC = Mac.getInstance("HmacSHA512");
                    secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA512");
                    sha512_HMAC.init(secret_key);
                    returnHash = sha512_HMAC.doFinal(value.getBytes());
                    break;
            }
        }catch (InvalidKeyException e){
            System.out.println(e.getMessage());
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }

        return returnHash;

    }

}
