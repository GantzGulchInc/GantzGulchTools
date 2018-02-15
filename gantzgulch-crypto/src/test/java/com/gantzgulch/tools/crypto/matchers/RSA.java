package com.gantzgulch.tools.crypto.matchers;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RSA {

    public static Matcher<KeyPair> equalTo(final KeyPair expectedRsaKeyPair) {

        return new TypeSafeMatcher<KeyPair>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return ").appendText( valueToString(expectedRsaKeyPair) );
            }

            @Override
            protected void describeMismatchSafely(final KeyPair item, final Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendText(valueToString(item));
            }

            @Override
            protected boolean matchesSafely(final KeyPair item) {

                final PrivateKey expectedPrivateKey = expectedRsaKeyPair.getPrivate();
                final PrivateKey itemPrivateKey = item.getPrivate();

                if (!Objects.equals(expectedPrivateKey.getAlgorithm(), itemPrivateKey.getAlgorithm())) {
                    return false;
                }

                final RSAPrivateKey expectedRSAPrivateKey = (RSAPrivateKey) expectedPrivateKey;
                final RSAPrivateKey itemRSAPrivateKey = (RSAPrivateKey) itemPrivateKey;

                if (!expectedRSAPrivateKey.getModulus().equals(itemRSAPrivateKey.getModulus())) {
                    return false;
                }

                if (!expectedRSAPrivateKey.getPrivateExponent().equals(itemRSAPrivateKey.getPrivateExponent())) {
                    return false;
                }
                
                return true;
            }

        };
    }

    public static Matcher<PublicKey> equalTo(final PublicKey expectedPublicKey) {

        return new TypeSafeMatcher<PublicKey>() {

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return ").appendText( valueToString(expectedPublicKey) );
            }

            @Override
            protected void describeMismatchSafely(final PublicKey item, final Description mismatchDescription) {
                mismatchDescription.appendText(" was ").appendText(valueToString(item));
            }

            @Override
            protected boolean matchesSafely(final PublicKey item) {

                if (!Objects.equals(expectedPublicKey.getAlgorithm(), item.getAlgorithm())) {
                    return false;
                }

                final RSAPublicKey expectedRSAPublicKey = (RSAPublicKey) expectedPublicKey;
                final RSAPublicKey itemRSAPrivateKey = (RSAPublicKey) item;

                if (!expectedRSAPublicKey.getModulus().equals(itemRSAPrivateKey.getModulus())) {
                    return false;
                }

                return true;
            }

        };
    }
    

    protected static String valueToString(final KeyPair keyPair) {
        
        final RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        
        final StringBuilder sb = new StringBuilder();
        
        sb.append("[KeyPair: PrivateKey:[");
        sb.append(" Alg: " ).append( privateKey.getAlgorithm());
        sb.append(", Modulus bits: " ).append(privateKey.getModulus().bitLength());
        sb.append(", Modulus: " ).append(privateKey.getModulus());
        sb.append(", Exp bits: " ).append(privateKey.getPrivateExponent().bitLength());
        sb.append(", Exp: " ).append(privateKey.getPrivateExponent());
        sb.append("] ]");
        
        return sb.toString();
    }

    protected static String valueToString(final PublicKey keyPair) {
        
        final RSAPublicKey privateKey = (RSAPublicKey)keyPair;
        
        final StringBuilder sb = new StringBuilder();
        
        sb.append("[PublicKey: [");
        sb.append(" Alg: " ).append( privateKey.getAlgorithm());
        sb.append(", Modulus bits: " ).append(privateKey.getModulus().bitLength());
        sb.append(", Modulus: " ).append(privateKey.getModulus());
        sb.append("]");
        
        return sb.toString();
    }
}
