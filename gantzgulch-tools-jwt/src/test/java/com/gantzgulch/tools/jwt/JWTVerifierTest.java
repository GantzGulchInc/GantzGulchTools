package com.gantzgulch.tools.jwt;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gantzgulch.tools.common.logging.GGLogger;
import com.gantzgulch.tools.crypto.alg.ec.ECReader;

public class JWTVerifierTest {

    private static final GGLogger LOG = GGLogger.getLogger(JWTVerifierTest.class);

    private final String PUBLIC_KEY_PEM = "" + //
            "-----BEGIN PUBLIC KEY-----\n" + //
            "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQAUEFAVAzzpG4ZR82nvl51AZvBfAg0\n" + //
            "NaWJpHUsXTK1bQmsxv4CCi87W/v3mp8+6dV2pDZ/TjLV0gWNWNBs1cwDiVABLmet\n" + //
            "lDsd9cZ6Sxt596SK6pedk9vM0JJGQsvl+65Xs7GVu13yontnPeb9MWIW6o/ZHA2j\n" + //
            "vk9K/foD2T3eAoYkZ5M=\n" + //
            "-----END PUBLIC KEY-----\n";

    @Test
    public void testValidSignature() {

        final PublicKey key = parsePublicKey(PUBLIC_KEY_PEM);
        final List<PublicKey> keys = new ArrayList<>();
        keys.add(key);

        final String token = "eyJhbGciOiJFUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwOi8vand0LmdhbnR6Z3VsY2guY29tIiwic3ViIjoiZ2FudHptIiwiYXVkIjoiR2FudHpHdWxjaCIsInVpZCI6IlMwNzkxMVAiLCJpYXQiOjE1NDEwODcxMDIsImV4cCI6MjU0MTA4NzE2MiwianRpIjoiOTNiZDg4MWMtYmQwMi00MzRiLWJlOGEtYjBiNjUxMTA5MGJiIn0.ABFmIUKET2fcRzg13IWNiCHpVa8QukMYxnXGRkqOPR1S_NozGXArBpRUddyuwY-f3m2LpwQ8x6nKsXLsow5BZZv2AY8XwEoafLYqGvgQ6y0WhPx3F43InFFDcmSZpza7GVvpALmqQdaGW_InPQb_BNJSCKRRTIaMBrLuiX4pk7hoeHtr";

        final JWTVerifier verifier = new JWTVerifier(30);

        final DecodedJWT jwt = verifier.verifyAndDecode(token, keys);

        System.out.println("Jwt: " + jwt);

        LOG.debug("Header: %s", jwt.getHeader());
        LOG.debug("Claims: %s", jwt.getClaims());
        
        for(Map.Entry<String, Claim> ce : jwt.getClaims().entrySet()) {
            
            LOG.debug("    claim: %s : %s / %f", ce.getKey(), ce.getValue().asString(), ce.getValue().asDouble());
        }
            
            
       

    }

    private PublicKey parsePublicKey(final String pem) {

        final PublicKey key = ECReader.readPublicKey(pem);

        LOG.debug("key: %s", key);

        return key;
    }

}
