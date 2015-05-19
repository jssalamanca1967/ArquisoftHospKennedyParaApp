package co.edu.uniandes.hospitalkennedy.security.jwt.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by oscarkiyoshigegarcesaparicio on 17/09/14.
 */
public class JsonWebToken {

    /**
     * Encode an Object in the JWT way. <JsonHeaders>.<JsonPayloadIn>.<Hash>
     * The JsonPayloadIn es a Json that must include 'exp' key, this key is
     * expiration DATE.
     *
     * @param payloadIn This is the Payload
     * @param key Secret Key to create the Hash.
     * @param algorithm Chosen algorithm to create the Hash.
     * @return Token
     */
    public static String encode(Object payloadIn, String key, JwtHashAlgorithm algorithm) {

        JsonObject jsonHeader = new JsonObject();
        jsonHeader.add("typ", new JsonPrimitive("JWT"));
        jsonHeader.add("alg", new JsonPrimitive(algorithm.toString()));

        String jsonPayloadIn = (new Gson()).toJson(payloadIn);

        byte[] header = new byte[0];
        byte[] payload = new byte[0];

        try {
            header = jsonHeader.toString().getBytes("UTF-8");
            payload = jsonPayloadIn.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String toSign = (Base64.encodeBase64URLSafeString(header)) + "." + (Base64.encodeBase64URLSafeString(payload));

        byte[] sign = HashAlgorithm.getHash(key, toSign, algorithm);

        String returnS = toSign + "." + (Base64.encodeBase64URLSafeString(sign));
        return returnS;

    }

    /**
     * Method to decode a token.
     *
     * @param token
     * @param key Secret Key used to decode the Hash.
     * @param verify Boolean that allows to verify Token, The developer can
     * choose if he wants to verify the token correctness.
     * @return String decoded.
     * @throws SignatureException This Exceptions is throw if something goes
     * wrong with Signature.
     */
    public static String decode(String token, String key, boolean verify) throws SignatureException {

        Gson gsonUtil = new Gson();
        
        System.out.println(token);
        System.out.println(key);

        String[] parts = token.split("\\.");
        String header = parts[0];
        String payload = parts[1];
        String noDecodedCrypto = parts[2];
        String decodedHeader = new String(Base64.decodeBase64(header));
        String decodedPayload = new String(Base64.decodeBase64(payload));
        JsonElement jsonHeader = gsonUtil.fromJson(decodedHeader, JsonElement.class);
        JsonObject jobjHeader = jsonHeader.getAsJsonObject();
        JsonElement jsonPayload = gsonUtil.fromJson(decodedPayload, JsonElement.class);
        JsonObject jobjPayload = jsonPayload.getAsJsonObject();

        if (verify) {
            String toSign = (header + "." + payload);
            JwtHashAlgorithm algorithm = JwtHashAlgorithm.valueOf(jobjHeader.get("alg").getAsString());
//            verifyToken(key, toSign, noDecodedCrypto, algorithm);

        }
        return jobjPayload.toString();
    }

    /**
     *
     * @param key
     * @param toSign
     * @param signature
     * @param algorithm
     * @return
     * @throws SignatureException
     */
    public static boolean verifyToken(String key, String toSign, String signature, JwtHashAlgorithm algorithm) throws SignatureException {

        byte[] toSignVer = HashAlgorithm.getHash(key, toSign, algorithm);

        String encodedSign = Base64.encodeBase64URLSafeString(toSignVer);
        
        System.out.println(encodedSign);

        if (!encodedSign.equals(signature)) {
            throw new SignatureException("Invalid Signature");
        }

        return true;

    }
}
