package com.gantzgulch.tools.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.GGReaders;
import com.gantzgulch.tools.jwt.impl.JWTAlgorithm;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JWT2Test {

    private static final GGLogger LOG = GGLogger.getLogger(JWT2Test.class);

    private ECPrivateKey privateKey;
    private ECPublicKey publicKey;

    private Map<String, PublicKey> publicKeyMap;

    private JWTVerifier2 jwtVerifier;

    @Before
    public void before() {

        final KeyPair keyPair = GGReaders.EC.readKeyPair(getClass().getResourceAsStream("/ecdsa-p521-private.pem"));
        privateKey = (ECPrivateKey) keyPair.getPrivate();
        publicKey = (ECPublicKey) keyPair.getPublic();

        publicKeyMap = new HashMap<String, PublicKey>();
        publicKeyMap.put("KID123", publicKey);

        jwtVerifier = JWTVerifier2.create(60, publicKeyMap);

    }

    @Test
    public void ES512Test() {

        final Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);

        final String token = JWTBuilder.create(JWTAlgorithm.ES512, privateKey)
                .withKeyId("KID123")
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(60)))
                .withNotBefore(Date.from(now))
                .withClaim("deviceId", "T452454")
                .withClaim("user", "gantzm")
                .withClaim("group", "G1AZ")
                .createAndSign();

        LOG.debug("Token: %s", token);

        DecodedJWT jwt = jwtVerifier.verifyAndDecode(token);

        assertThat(jwt.getIssuedAt(), equalTo(Date.from(now)));
        assertThat(jwt.getExpiresAt(), equalTo(Date.from(now.plusSeconds(60))));
        assertThat(jwt.getNotBefore(), equalTo(Date.from(now)));

        assertThat(jwt.getClaim("deviceId").asString(), equalTo("T452454"));
        assertThat(jwt.getClaim("user").asString(), equalTo("gantzm"));
        assertThat(jwt.getClaim("group").asString(), equalTo("G1AZ"));
    }

}
