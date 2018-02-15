package com.gantzgulch.tools.common.hash;

import java.security.MessageDigest;
import java.util.Base64;

import com.gantzgulch.tools.common.lang.Bytes;

public final class Hash {

	private Hash() {
		throw new UnsupportedOperationException();
	}

	public static byte[] hash(final HashAlgorithm alg, final byte[] bytes) {
		
		if( bytes == null ){
			return null;
		}

		final MessageDigest digest = alg.create();

	    return digest.digest(bytes);

	}
	
	public static String hashHex(final HashAlgorithm alg, final byte[] bytes) {
		
	    return Bytes.toHex(hash(alg, bytes));
	}
	
	
	public static String hashBase64(final HashAlgorithm alg, final byte[] bytes) {
		
	    return Base64.getUrlEncoder().encodeToString(hash(alg, bytes));
	}
	
	
	public static byte[] sha256(final byte[] bytes) {
		return hash(HashAlgorithm.SHA_256, bytes);
	}

	public static byte[] sha384(final byte[] bytes) {
		return hash(HashAlgorithm.SHA_384, bytes);
	}
	
	public static byte[] sha512(final byte[] bytes) {
		return hash(HashAlgorithm.SHA_512, bytes);
	}

	
	public static String sha256Hex(final byte[] bytes) {
		return hashHex(HashAlgorithm.SHA_256, bytes);
	}

	public static String sha384Hex(final byte[] bytes) {
		return hashHex(HashAlgorithm.SHA_384, bytes);
	}
	
	public static String sha512Hex(final byte[] bytes) {
		return hashHex(HashAlgorithm.SHA_512, bytes);
	}


	public static String sha256Base64(final byte[] bytes) {
		return hashBase64(HashAlgorithm.SHA_256, bytes);
	}

	public static String sha384Base64(final byte[] bytes) {
		return hashBase64(HashAlgorithm.SHA_384, bytes);
	}
	
	public static String sha512Base64(final byte[] bytes) {
		return hashBase64(HashAlgorithm.SHA_512, bytes);
	}
}
