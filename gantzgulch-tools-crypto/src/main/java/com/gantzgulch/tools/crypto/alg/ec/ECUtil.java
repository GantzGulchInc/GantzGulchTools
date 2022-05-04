package com.gantzgulch.tools.crypto.alg.ec;

import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Optional;

import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.common.lang.Cast;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public final class ECUtil {

    static {
        BouncyCastleState.init();
    }

    private static final GGLogger LOG = GGLogger.getLogger(ECUtil.class);
    
    private ECUtil() {
        throw new UnsupportedOperationException();
    }

    
    public static Optional<String> deriveNistCurveName(final ECParameterSpec ecParameterSpec) {
        
        String result = null;
        
        for (Enumeration<String> names = Cast.cast(NISTNamedCurves.getNames()); names.hasMoreElements();){

            final String name = names.nextElement();

            ECNamedCurveParameterSpec params = ECNamedCurveTable.getParameterSpec(name);
            
            if (params.getN().equals(ecParameterSpec.getN())
                && params.getH().equals(ecParameterSpec.getH())
                && params.getCurve().equals(ecParameterSpec.getCurve())
                && params.getG().equals(ecParameterSpec.getG())){
                
                result = name;
            }
        }

        return Optional.ofNullable(result);
    }

    public static byte[] convertRawSignatureToDer(final byte[] rawRS) {
        
        // construct the ASN1Sequence with r and s
        ByteArrayOutputStream outs = new ByteArrayOutputStream();

        LOG.trace("convertRawSignatureToDer: signed[0]: %x", rawRS[0]);
        LOG.trace("convertRawSignatureToDer: signed[32]: %x", rawRS[32]);

        byte radd = (byte) (((rawRS[0] & 0x80) > 0) ? 1 : 0);
        byte sadd = (byte) (((rawRS[32] & 0x80) > 0) ? 1 : 0);

        LOG.trace("convertRawSignatureToDer: radd: %d", radd);
        LOG.trace("convertRawSignatureToDer: sadd: %d", sadd);
        
        byte length = (byte) (0x44 + radd + sadd);

        outs.write(0x30);
        outs.write(length); // length 68 bytes +
        outs.write(0x02); // ASN1Integer
        outs.write(0x20 + radd); // length 32 bytes
        if (radd > 0)
            outs.write(0x00); // positive val
        outs.write(rawRS, 0, 32);
        outs.write(0x02); // ASN1Integer
        outs.write(0x20 + sadd); // length 32 bytes
        if (sadd > 0)
            outs.write(0x00); // positive val
        outs.write(rawRS, 32, 32);

        return outs.toByteArray();
        
    }
}
