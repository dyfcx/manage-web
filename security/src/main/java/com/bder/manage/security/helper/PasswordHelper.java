package com.bder.manage.security.helper;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.util.password.PasswordEncryptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Felix YF Dong
 * @date 2020/11/25
 */
public class PasswordHelper {
    public enum Type{
        SHA256_BASE64("SHA-256", "base64"),
        SHA256_HEX("SHA-256", "hexadecimal"),
        MD5_BASE64("MD5", "base64"),
        MD5_HEX("MD5", "hexadecimal");

        private String algorithm;
        private String outputType;

        private Type(String algorithm, String value) {
            this.algorithm = algorithm;
            this.outputType = value;
        }

        public String getAlgorithm() {
            return algorithm;
        }

        public String getOutputType() {
            return outputType;
        }
    }

    private final static class MyPasswordEncryptor implements PasswordEncryptor {

        private final StandardStringDigester digester;

        public MyPasswordEncryptor(String algorithm) {
            this.digester = new StandardStringDigester();
            this.digester.setAlgorithm(algorithm);
            this.digester.setIterations(1000);
            this.digester.setSaltSizeBytes(8);
        }

        public void setStringOutputType(final String stringOutputType) {
            this.digester.setStringOutputType(stringOutputType);
        }

        @Override
        public String encryptPassword(final String password) {
            return this.digester.digest(password);
        }

        @Override
        public boolean checkPassword(final String plainPassword,
                                     final String encryptedPassword) {
            return this.digester.matches(plainPassword, encryptedPassword);
        }
    }

    private static Map<Type, MyPasswordEncryptor> passwordEncryptors = new HashMap<Type, MyPasswordEncryptor>();

    private PasswordHelper(){}

    private static PasswordEncryptor getPasswordEncryptor(final Type type) {
        if (passwordEncryptors.get(type) == null) {
            synchronized (passwordEncryptors) {
                if (passwordEncryptors.get(type) == null) {
                    MyPasswordEncryptor passwordEncryptor = new MyPasswordEncryptor(type.getAlgorithm());
                    passwordEncryptor.setStringOutputType(type.getOutputType());
                    passwordEncryptors.put(type, passwordEncryptor);

                }
            }
        }

        return passwordEncryptors.get(type);
    }

    public static String encryptPassword(final String password) {
        return getPasswordEncryptor(Type.SHA256_BASE64).encryptPassword(password);
    }

    public static String encryptPassword(final String password, final Type type) {
        return getPasswordEncryptor(type).encryptPassword(password);
    }

    public static boolean checkPassword(final String plainPassword,
                                        final String encryptedPassword) {
        return getPasswordEncryptor(Type.SHA256_BASE64).checkPassword(plainPassword, encryptedPassword);
    }

    public static boolean checkPassword(final String plainPassword,
                                        final String encryptedPassword, final Type type) {
        return getPasswordEncryptor(type).checkPassword(plainPassword, encryptedPassword);
    }
}
