package com.gantzgulch.tools.common.hash;

import java.security.MessageDigest;

import com.gantzgulch.tools.common.codec.GGBase64;
import com.gantzgulch.tools.common.codec.GGHex;

public final class GGHash {

	private GGHash() {
		throw new UnsupportedOperationException();
	}

	public static byte[] hash(final GGHashAlgorithm alg, final byte[] bytes) {
		
		if( bytes == null ){
			return null;
		}

		final MessageDigest digest = alg.create();

	    return digest.digest(bytes);

	}
	
	public static String hashToHex(final GGHashAlgorithm alg, final byte[] bytes) {
		
	    return GGHex.toHexString(hash(alg, bytes));
	}
	
	
	public static String hashToBase64(final GGHashAlgorithm alg, final byte[] bytes, final boolean urlFriendly) {
		
	    return GGBase64.toBase64String(hash(alg, bytes), urlFriendly);
	}
	
	
	public static byte[] sha256(final byte[] bytes) {
		return hash(GGHashAlgorithm.SHA_256, bytes);
	}

	public static byte[] sha384(final byte[] bytes) {
		return hash(GGHashAlgorithm.SHA_384, bytes);
	}
	
	public static byte[] sha512(final byte[] bytes) {
		return hash(GGHashAlgorithm.SHA_512, bytes);
	}

	
	
	public static String sha256Hex(final byte[] bytes) {
		return hashToHex(GGHashAlgorithm.SHA_256, bytes);
	}

	public static String sha384Hex(final byte[] bytes) {
		return hashToHex(GGHashAlgorithm.SHA_384, bytes);
	}
	
	public static String sha512Hex(final byte[] bytes) {
		return hashToHex(GGHashAlgorithm.SHA_512, bytes);
	}


	
	public static String sha256Base64(final byte[] bytes) {
		return hashToBase64(GGHashAlgorithm.SHA_256, bytes, false);
	}

	public static String sha384Base64(final byte[] bytes) {
		return hashToBase64(GGHashAlgorithm.SHA_384, bytes, false);
	}
	
	public static String sha512Base64(final byte[] bytes) {
		return hashToBase64(GGHashAlgorithm.SHA_512, bytes, false);
	}

	
	
	public static String sha256Base64Url(final byte[] bytes) {
        return hashToBase64(GGHashAlgorithm.SHA_256, bytes, true);
    }

    public static String sha384Base64Url(final byte[] bytes) {
        return hashToBase64(GGHashAlgorithm.SHA_384, bytes, true);
    }
    
    public static String sha512Base64Url(final byte[] bytes) {
        return hashToBase64(GGHashAlgorithm.SHA_512, bytes, true);
    }
}
