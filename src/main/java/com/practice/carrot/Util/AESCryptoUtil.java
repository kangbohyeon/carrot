package com.practice.carrot.Util;

import com.practice.carrot.login.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;



public class AESCryptoUtil {
//    Logger logger = LogManager.getLogger(AESCryptoUtil.class);
//    logger.info("123");
    private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
//    private static final String INSTANCE_TYPE = "AES/CBC/" +
//            ""




}
