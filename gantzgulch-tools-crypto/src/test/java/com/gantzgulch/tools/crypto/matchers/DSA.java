package com.gantzgulch.tools.crypto.matchers;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class DSA {

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

                final DSAPrivateKey expectedRSAPrivateKey = (DSAPrivateKey) expectedPrivateKey;
                final DSAPrivateKey itemRSAPrivateKey = (DSAPrivateKey) itemPrivateKey;

                if (!expectedRSAPrivateKey.getX().equals(itemRSAPrivateKey.getX())) {
                    return false;
                }

                if (!expectedRSAPrivateKey.getParams().getG() .equals(itemRSAPrivateKey.getParams().getG())) {
                    return false;
                }
                
                if (!expectedRSAPrivateKey.getParams().getP() .equals(itemRSAPrivateKey.getParams().getP())) {
                    return false;
                }

                if (!expectedRSAPrivateKey.getParams().getQ() .equals(itemRSAPrivateKey.getParams().getQ())) {
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

                final DSAPublicKey expectedRSAPublicKey = (DSAPublicKey) expectedPublicKey;
                final DSAPublicKey itemRSAPrivateKey = (DSAPublicKey) item;

                if (!expectedRSAPublicKey.getY().equals(itemRSAPrivateKey.getY())) {
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
