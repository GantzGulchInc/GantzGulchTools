package com.gantzgulch.tools.jwt;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.GGReaders;
import com.gantzgulch.tools.jwt.impl.JWTAlgorithm;

public class JWTTest {

    private static final GGLogger LOG = GGLogger.getLogger(JWTTest.class);

    private SecureRandom random;
    
    private KeyPair keyPair;
    private ECPrivateKey privateKey;
    private ECPublicKey publicKey;
    
    private byte[] secret;
    private JWTVerifier jwtVerifier;
    
    @Before
    public void before() {
        
        random = new SecureRandom();
        
        
        keyPair = GGReaders.EC.readKeyPair( getClass().getResourceAsStream("/ecdsa-p521-private.pem"));
        privateKey = (ECPrivateKey)keyPair.getPrivate();
        publicKey = (ECPublicKey)keyPair.getPublic();

        secret = new byte[20];
        random.nextBytes(secret);
        
        jwtVerifier = JWTVerifier.create(60, new JWTKey(secret), new JWTKey(publicKey));
        
    }
    
    @Test
    public void ES512Test() {

        final Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        
        final String token = JWTBuilder.create(JWTAlgorithm.ES512, privateKey) //
                .withIssuedAt( Date.from(now) ) //
                .withExpiresAt( Date.from( now.plusSeconds(60) ) ) //
                .withNotBefore( Date.from(now) ) //
                .withClaim("deviceId", "T452454") //
                .withClaim("user", "gantzm") //
                .withClaim("group", "G1AZ") //
                .createAndSign();
        
        LOG.debug("Token: %s", token);

        DecodedJWT jwt = jwtVerifier.verifyAndDecode(token);
        
        assertThat(jwt.getIssuedAt(), equalTo( Date.from(now)) );
        assertThat(jwt.getExpiresAt(), equalTo( Date.from(now.plusSeconds(60))));
        assertThat(jwt.getNotBefore(), equalTo( Date.from(now)));
        
        assertThat(jwt.getClaim("deviceId").asString(), equalTo("T452454"));
        assertThat(jwt.getClaim("user").asString(), equalTo("gantzm"));
        assertThat(jwt.getClaim("group").asString(), equalTo("G1AZ"));
    }

    @Test
    public void HS512Test() {

        final Instant now = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        
        final String token = JWTBuilder.create(JWTAlgorithm.HS512, secret) //
                .withIssuedAt( Date.from(now) ) //
                .withExpiresAt( Date.from( now.plusSeconds(60) ) ) //
                .withNotBefore( Date.from(now) ) //
                .withClaim("deviceId", "T452454") //
                .withClaim("user", "gantzm") //
                .withClaim("group", "G1AZ") //
                .createAndSign();
        
        LOG.debug("Token: %s", token);

        DecodedJWT jwt = jwtVerifier.verifyAndDecode(token);
        
        assertThat(jwt.getIssuedAt(), equalTo( Date.from(now)) );
        assertThat(jwt.getExpiresAt(), equalTo( Date.from(now.plusSeconds(60))));
        assertThat(jwt.getNotBefore(), equalTo( Date.from(now)));
        
        assertThat(jwt.getClaim("deviceId").asString(), equalTo("T452454"));
        assertThat(jwt.getClaim("user").asString(), equalTo("gantzm"));
        assertThat(jwt.getClaim("group").asString(), equalTo("G1AZ"));
    }

}
