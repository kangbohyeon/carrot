package com.practice.carrot.aesCryption;

import com.practice.carrot.session.SessionRespository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.function.Predicate;


@Service
public class AESService {
    Logger logger = LogManager.getLogger(AESService.class);
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
    private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;
    private IvParameterSpec ivParameterSpec;

    @Autowired
    SessionRespository sessionRespository;

    public void keygeneration(final String key) {
        validation(key);
        try {
            byte[] keyBytes = key.getBytes(ENCODING_TYPE);
            secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            cipher = Cipher.getInstance(INSTANCE_TYPE);
            ivParameterSpec = new IvParameterSpec(keyBytes);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            logger.info("key generation error");
        }
    }

    public  String encrypt(final String str,Long keys) throws Exception {
        keygeneration(sessionRespository.findById(keys).get().getSession());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
        String encrypttext = new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
        return encrypttext;
    }

    public String decrypt(final String str,Long keys) throws Exception {
        keygeneration(sessionRespository.findById(keys).get().getSession());
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
        String decrypttext=new String(cipher.doFinal(decoded), ENCODING_TYPE);
        sessionRespository.deleteById(keys);
        return decrypttext;
    }

    private void validation(final String key) {
        Optional.ofNullable(key)
                .filter(Predicate.not(String::isBlank))
                .filter(Predicate.not(s -> s.length() != 16))
                .orElseThrow(IllegalArgumentException::new);
    }

}
