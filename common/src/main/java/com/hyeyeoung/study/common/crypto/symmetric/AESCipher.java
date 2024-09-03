package com.hyeyeoung.study.common.crypto.symmetric;

import com.hyeyeoung.study.common.crypto.symmetric.enums.AESKeySize;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public abstract class AESCipher {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16; // 128 bits = 16 bytes for IV

    private final SecretKey secretKey;

    protected AESCipher(AESKeySize keySize, byte[] keyBytes) {
        validateKeyString(keySize, keyBytes);
        this.secretKey = generateKey(keySize, keyBytes);
    }

    private SecretKey generateKey(AESKeySize keySize, byte[] keyBytes) {
        byte[] newKeyBytes = Arrays.copyOf(keyBytes, keySize.getByteSize());
        return new SecretKeySpec(newKeyBytes, ALGORITHM);
    }

    private void validateKeyString(AESKeySize aesKeySize, byte[] keyBytes) {
        if (keyBytes.length < aesKeySize.getByteSize()) {
            throw new IllegalArgumentException("The key size must be at least " + aesKeySize.getByteSize() + " bytes.");
        }
    }

    // Encrypt
    public String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivSpec = generateIv(); // Generate a new IV for each encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] ivAndEncrypted = concatenate(ivSpec.getIV(), encryptedBytes);

        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    // Decrypt
    public String decrypt(String encryptedText) throws Exception {
        // Split the IV and encrypted message
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] ivBytes = Arrays.copyOfRange(decodedBytes, 0, IV_SIZE);
        byte[] encryptedBytes = Arrays.copyOfRange(decodedBytes, IV_SIZE, decodedBytes.length);

        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Generate IV (random for each encryption)
    private IvParameterSpec generateIv() {
        byte[] ivBytes = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

    // Utility method to concatenate IV and encrypted data
    private byte[] concatenate(byte[] iv, byte[] encrypted) {
        byte[] result = Arrays.copyOf(iv, iv.length + encrypted.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
        return result;
    }

}
