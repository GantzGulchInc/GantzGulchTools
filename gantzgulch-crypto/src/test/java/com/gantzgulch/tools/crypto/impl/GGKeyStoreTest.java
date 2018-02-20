package com.gantzgulch.tools.crypto.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.security.Key;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.interfaces.RSAPrivateKey;

import org.junit.Test;

import com.gantzgulch.tools.common.logging.GGLogger;

public class GGKeyStoreTest {

    private static final GGLogger LOG = GGLogger.getLogger(GGKeyStoreTest.class);

    @Test
    public void create() {

        final GGKeyStore keyStore = new GGKeyStore();

        assertThat(keyStore, notNullValue());
        assertThat(keyStore.getKeyStore(), notNullValue());
        assertThat(keyStore.getPassword(), notNullValue());
    }

    @Test
    public void loadCertificate() {

        final GGKeyStore keyStore = new GGKeyStore();

        final String certFilename = getClass().getResource("/keys/key1-cert.pem").getFile();

        keyStore.setCertificateEntryFromFile("alias1", certFilename);
    }

    @Test
    public void loadKeyAndCertificate() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {

        final GGKeyStore keyStore = new GGKeyStore();

        final String keyFilename = getClass().getResource("/keys/key1-private.pem").getFile();
        final String certFilename = getClass().getResource("/keys/key1-cert.pem").getFile();

        keyStore.setCertificateEntryFromFile("alias1", certFilename);
        keyStore.setKeyEntryFromFile("alias2", keyFilename, certFilename);

        final Key key = keyStore.getKeyStore().getKey("alias2", keyStore.getPassword().toCharArray());

        assertThat(key, notNullValue());

        LOG.debug("key: format ...: %s", key.getFormat());
        LOG.debug("key: class ....: %s", key.getClass());

        final RSAPrivateKey privateKey = (RSAPrivateKey) key;

        LOG.debug("key: size .....: %d", privateKey.getModulus().bitLength());
        LOG.debug("key: modulus ..: %s", modInHex(privateKey));
        assertThat(privateKey, notNullValue());
        assertThat(modInHex(privateKey), equalTo(
                "00:e9:35:e3:61:8d:cc:1c:44:78:75:a2:74:47:d5:05:5d:6b:b3:cb:64:5a:04:43:05:fb:10:51:d2:91:61:2f:5a:a2:17:26:80:87:19:55:44:cb:e2:f9:11:14:92:c7:83:6f:d5:3d:a0:72:61:64:fa:09:8b:59:81:28:43:46:b8:cf:c1:bb:62:c2:02:19:dd:8a:7c:24:ea:0d:00:a4:a2:f5:9f:85:c9:a6:3a:e1:7d:e2:08:d8:76:ca:83:54:c9:50:05:2b:03:c2:95:9d:66:61:c8:14:4b:7a:68:f8:54:96:36:58:db:9a:e4:39:36:3b:83:f4:5f:c6:e5:65:4d:be:71:c5:a8:cd:20:0f:19:2d:51:5c:1a:9f:39:54:d7:ce:1d:55:6e:d7:cd:f3:d2:a3:5f:ef:cd:21:09:70:76:76:69:15:3b:9a:e0:97:b8:41:b8:28:0b:d1:02:a6:ff:29:7d:29:41:38:2b:82:6c:d8:a8:53:6b:e5:d5:7d:d2:64:59:d0:f0:a6:03:32:3b:8a:4e:54:f9:7d:d8:5b:f3:23:4f:1d:fc:a6:b6:a5:bf:b8:2e:e2:2b:dc:21:92:9e:cc:ae:81:cf:5f:e2:72:e2:61:c6:9f:56:37:28:e4:32:5e:97:4c:7e:48:e1:20:31:4f:98:6d:a6:f3:76:b4:cb"));

    }

    private String modInHex(final RSAPrivateKey key) {

        final StringBuilder b = new StringBuilder();

        for (final byte bv : key.getModulus().toByteArray()) {

            if (b.length() > 0) {
                b.append(":");
            }

            b.append(String.format("%02x", bv));

        }

        return b.toString();
    }

}
