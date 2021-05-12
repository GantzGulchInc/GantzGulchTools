package com.gantzgulch.tools.crypto.alg.ec;

import java.util.Enumeration;
import java.util.Optional;

import org.bouncycastle.asn1.nist.NISTNamedCurves;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;

import com.gantzgulch.tools.common.lang.Cast;
import com.gantzgulch.tools.crypto.BouncyCastleState;

public final class ECUtil {

    static {
        BouncyCastleState.init();
    }

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

}
