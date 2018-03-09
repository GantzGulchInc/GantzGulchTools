package com.gantzgulch.tools.crypto.password;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public class PBKDF2Hash {
    
    private static final Pattern INPUT_PATTERN = Pattern.compile("(\\d+):([a-zA-Z0-9]+):([a-zA-Z0-9]+)");
    
    private final int iterations;
    private final byte[] salt;
    private final byte[] hash;
    
    public PBKDF2Hash(final int iterations, final byte[] salt, final byte[] hash) {
        this.iterations = iterations;
        this.salt = salt;
        this.hash = hash;
    }

    public PBKDF2Hash(final String hash) {
        
        final Matcher matcher = INPUT_PATTERN.matcher(hash);
        
        if( !matcher.matches() ){
            throw new CryptoException("Invalid input: " + hash);
        }
        
        this.iterations = Integer.parseInt(matcher.group(1));
        this.salt = GGHex.fromHexString(matcher.group(2));
        this.hash = GGHex.fromHexString(matcher.group(3));
    }

    public int getIterations() {
        return iterations;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getHash() {
        return hash;
    }
    
    public String toStandardFormat() {
        return String.format("%d:%s:%s", iterations, GGHex.toHexString(salt), GGHex.toHexString(hash));
    }

    @Override
    public String toString() {
        return toStandardFormat();
    }
}