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
	
	public static String hashHex(final GGHashAlgorithm alg, final byte[] bytes) {
		
	    return GGHex.toHexString(hash(alg, bytes));
	}
	
	
	public static String hashBase64(final GGHashAlgorithm alg, final byte[] bytes) {
		
	    return GGBase64.toBase64String(hash(alg, bytes));
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
		return hashHex(GGHashAlgorithm.SHA_256, bytes);
	}

	public static String sha384Hex(final byte[] bytes) {
		return hashHex(GGHashAlgorithm.SHA_384, bytes);
	}
	
	public static String sha512Hex(final byte[] bytes) {
		return hashHex(GGHashAlgorithm.SHA_512, bytes);
	}


	public static String sha256Base64(final byte[] bytes) {
		return hashBase64(GGHashAlgorithm.SHA_256, bytes);
	}

	public static String sha384Base64(final byte[] bytes) {
		return hashBase64(GGHashAlgorithm.SHA_384, bytes);
	}
	
	public static String sha512Base64(final byte[] bytes) {
		return hashBase64(GGHashAlgorithm.SHA_512, bytes);
	}
}
