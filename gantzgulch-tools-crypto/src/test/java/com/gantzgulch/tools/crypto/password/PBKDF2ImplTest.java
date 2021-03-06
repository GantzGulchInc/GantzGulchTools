package com.gantzgulch.tools.crypto.password;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.GGPasswordDigests;

public class PBKDF2ImplTest {

    private static final GGLogger LOG = GGLogger.getLogger(PBKDF2ImplTest.class);
    @Test
    public void hashAndVerify() {

        final PBKDF2Hash pbHash = GGPasswordDigests.PBKDF2.hash(1000, "developerPassword");
        
        assertThat(pbHash, notNullValue());
        LOG.debug("hashAndVerify: pbHash: %s", pbHash);
        

        final boolean verified = GGPasswordDigests.PBKDF2.verify("developerPassword", pbHash);
        assertThat(verified, equalTo(true));

        final String pbHashString = pbHash.toStandardFormat();
        
        final PBKDF2Hash pbHash2 = new PBKDF2Hash(pbHashString);

        final boolean verified2 = GGPasswordDigests.PBKDF2.verify("developerPassword", pbHash2);
        assertThat(verified2, equalTo(true));
    }

    
    @Test
    public void verifyExistingHash() {

        final PBKDF2Hash pbHash = new PBKDF2Hash("100:2fa074da25b59967f171db57318dad6a:42c2e495a9ea67e40229ea019f474c147624b1e7970943d2c22cb6e39b646f9f3cae2a0d54033297e7baf53aab63e2be5ed963d85bef0ca5313e6c2edb717f92");
        
        final boolean verified = GGPasswordDigests.PBKDF2.verify("developerPassword", pbHash);
        assertThat(verified, equalTo(true));

    }

}
