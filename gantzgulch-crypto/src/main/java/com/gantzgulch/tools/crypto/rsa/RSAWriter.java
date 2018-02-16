package com.gantzgulch.tools.crypto.rsa;

import java.io.IOException;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.PublicKey;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import com.gantzgulch.tools.common.lang.Arguments;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public final class RSAWriter {

	static {
		BouncyCastleState.init();
	}

	private RSAWriter() {
	    throw new UnsupportedOperationException();
	}

	public static String writeToPEM(final KeyPair keyPair) throws IOException {

	    Arguments.isNotNull(keyPair, "keyPair is required to be non null.");

		return writeObjectToPEM(keyPair.getPrivate());

	}

	public static String writeToPEM(final PublicKey publicKey) throws IOException {

        Arguments.isNotNull(publicKey, "publicKey is required to be non null.");

		return writeObjectToPEM(publicKey);
	}

	private static String writeObjectToPEM(final Object object) throws IOException {

		final StringWriter stringWriter = new StringWriter();

		final JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);

		pemWriter.writeObject(object);
		pemWriter.close();

		return stringWriter.toString();
	}

}
