package com.gantzgulch.tools.crypto.impl;

import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import com.gantzgulch.tools.crypto.exception.CryptoException;
import com.gantzgulch.tools.crypto.pem.PEMReader;
import com.gantzgulch.tools.crypto.pkcs10.PKCS10Reader;

public class GGKeyStore {

    private KeyStore keyStore;

    private String password;

    public GGKeyStore() {

        try {
            this.keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            this.keyStore.load(null);
        } catch (final KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new CryptoException(e);
        }

        password = new BigInteger(128, new SecureRandom()).toString(32);
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public String getPassword() {
        return password;
    }

    public void setCertificateEntryFromFile(final String alias, final String certFilename) {

        try (Reader reader = Files.newBufferedReader(Paths.get(certFilename), Charset.forName("UTF-8"))) {

            final Certificate cert = PKCS10Reader.readCertificate(reader);

            setCertificateEntry(alias, cert);

        } catch (final IOException e) {
            throw new CryptoException(e);
        }
    }

    public void setCertificateEntryFromPem(final String alias, final String certPem) {

        final Certificate cert = PKCS10Reader.readCertificate(certPem);

        setCertificateEntry(alias, cert);

    }

    public void setCertificateEntry(final String alias, final Certificate cert) {

        try {

            keyStore.setCertificateEntry(alias, cert);

        } catch (final KeyStoreException e) {
            throw new CryptoException(e);
        }
    }

    public void setKeyEntryFromFile(final String alias, final String privateKeyFilename, final String certFilename) {

        try (Reader keyReader = Files.newBufferedReader(Paths.get(privateKeyFilename), Charset.forName("UTF-8"));
                Reader certReader = Files.newBufferedReader(Paths.get(certFilename), Charset.forName("UTF-8"))) {

            final PrivateKey privateKey = PEMReader.readKeyPair(keyReader).getPrivate();
            final Certificate certificate = PKCS10Reader.readCertificate(certReader);

            setKeyEntry(alias, privateKey, certificate);

        } catch (final IOException e) {
            throw new CryptoException(e);
        }
    }

    public void setKeyEntryFromPem(final String alias, final String keyPairPem, final String certPem) {

        final PrivateKey privateKey = PEMReader.readKeyPair(keyPairPem).getPrivate();

        final Certificate certificate = PKCS10Reader.readCertificate(certPem);

        setKeyEntry(alias, privateKey, certificate);

    }

    public void setKeyEntry(final String alias, final PrivateKey key, final Certificate certificate) {

        try {

            keyStore.setKeyEntry(alias, key, password.toCharArray(), new Certificate[] { certificate });

        } catch (final KeyStoreException e) {
            throw new CryptoException(e);
        }
    }

}
