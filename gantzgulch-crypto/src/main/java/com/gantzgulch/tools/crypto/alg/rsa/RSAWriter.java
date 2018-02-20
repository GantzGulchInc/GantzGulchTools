package com.gantzgulch.tools.crypto.alg.rsa;

import java.io.IOException;
import java.security.KeyPair;
import java.security.PublicKey;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.pem.PEMWriter;

public final class RSAWriter {

	static {
		BouncyCastleState.init();
	}

	private RSAWriter() {
	    throw new UnsupportedOperationException();
	}

	public static String writeToPEM(final KeyPair keyPair) throws IOException {

	    Arguments.isNotNull(keyPair, "keyPair is required to be non null.");

        return PEMWriter.write(keyPair);

	}

	public static String writeToPEM(final PublicKey publicKey) throws IOException {

        Arguments.isNotNull(publicKey, "publicKey is required to be non null.");

		return PEMWriter.write(publicKey);
	}

}
