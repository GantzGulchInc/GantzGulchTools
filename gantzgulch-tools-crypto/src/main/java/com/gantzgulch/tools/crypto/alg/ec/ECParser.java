package com.gantzgulch.tools.crypto.alg.ec;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

import javax.management.openmbean.InvalidKeyException;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;

import com.gantzgulch.logging.core.GGLogger;

public class ECParser {

    public static ECParser INSTANCE = new ECParser();
    
    private static final GGLogger LOG = GGLogger.getLogger(ECParser.class);
    
    private ECParser() {
    }
    
    public ECPublicKey parsePublicKey(final String curveName, final String xyHex) throws GeneralSecurityException {
        
        if( xyHex == null || xyHex.length() != 128) {
            throw new InvalidKeyException("xyHex must have length of 128");
        }
        
        final String xHex = xyHex.substring(0, 64);
        final String yHex = xyHex.substring(64,128);
        
        return parsePublicKey(curveName, xHex, yHex);
        
    }
    
    
    public ECPublicKey parsePublicKey(final String curveName, final String xHex, final String yHex) throws GeneralSecurityException {

        KeyFactory eckf = KeyFactory.getInstance("EC");

        final BigInteger x = new BigInteger(xHex, 16);
        final BigInteger y = new BigInteger(yHex, 16);

        LOG.info("parsePublicKey xHex: %s", xHex);
        LOG.info("parsePublicKey yHex: %s", yHex);
        LOG.info("parsePublicKey x: %s", x);
        LOG.info("parsePublicKey y: %s", y);
        
        ECPoint point = new ECPoint(x, y);

        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec(curveName);

        ECParameterSpec spec = new ECNamedCurveSpec(curveName, //
                parameterSpec.getCurve(), //
                parameterSpec.getG(), //
                parameterSpec.getN(), //
                parameterSpec.getH(), //
                parameterSpec.getSeed());

        ECPublicKey ecPublicKey = (ECPublicKey) eckf.generatePublic(new ECPublicKeySpec(point, spec));

        return ecPublicKey;
    }
    
    public String publicKeyToXYHex(final ECPublicKey publicKey) {

            final ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
            
            ECPoint point = ecPublicKey.getW();

            final String x = point.getAffineX().toString(16);
            final String y = point.getAffineY().toString(16);
            
            return x + y;
        
    }
}
