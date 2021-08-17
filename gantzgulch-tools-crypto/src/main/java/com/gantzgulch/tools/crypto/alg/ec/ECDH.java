package com.gantzgulch.tools.crypto.alg.ec;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyAgreement;

import com.gantzgulch.tools.crypto.BouncyCastleState;
import com.gantzgulch.tools.crypto.exception.CryptoException;

public final class ECDH {

    static {
        BouncyCastleState.init();
    }

    private ECDH() {
        throw new UnsupportedOperationException();
    }

  public static byte[] generateECDH(final PrivateKey privateKey, final PublicKey publicKey) {

      try {

          final KeyAgreement ka = KeyAgreement.getInstance("ECDH", "BC");

          ka.init(privateKey);
          
          ka.doPhase(publicKey, true);

          return ka.generateSecret();

      } catch (final GeneralSecurityException e) {
          throw new CryptoException(e);
      }
  }

}
