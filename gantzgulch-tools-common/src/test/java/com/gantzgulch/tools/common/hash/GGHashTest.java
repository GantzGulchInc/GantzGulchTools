package com.gantzgulch.tools.common.hash;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.gantzgulch.tools.common.codec.GGHex;
import com.gantzgulch.tools.common.lang.GGStrings;

public class GGHashTest {

	@Test
	public void sha256() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final byte[] expectedHash = GGHex.fromHexString("71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73");
		
		final byte[] hash = GGHashes.SHA_256.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha256_null() {

		final byte[] testBytes = null;

		final byte[] expectedHash = null;
		
		final byte[] hash = GGHashes.SHA_256.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha384() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final byte[] expectedHash = GGHex.fromHexString("feb67349df3db6f5924815d6c3dc133f091809213731fe5c7b5f4999e463479ff2877f5f2936fa63bb43784b12f3ebb4");
		
		final byte[] hash = GGHashes.SHA_384.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha384_null() {

		final byte[] testBytes = null;

		final byte[] expectedHash = null;
		
		final byte[] hash = GGHashes.SHA_384.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha512() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final byte[] expectedHash = GGHex.fromHexString("4DBFF86CC2CA1BAE1E16468A05CB9881C97F1753BCE3619034898FAA1AABE429955A1BF8EC483D7421FE3C1646613A59ED5441FB0F321389F77F48A879C7B1F1");
		
		final byte[] hash = GGHashes.SHA_512.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha512_null() {

		final byte[] testBytes = null;

		final byte[] expectedHash = null;
		
		final byte[] hash = GGHashes.SHA_512.hash(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}
	
	@Test
	public void sha256Hex() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73";
		
		final String hash = GGHashes.SHA_256.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha256Hex_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_256.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha384Hex() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "feb67349df3db6f5924815d6c3dc133f091809213731fe5c7b5f4999e463479ff2877f5f2936fa63bb43784b12f3ebb4";
		
		final String hash = GGHashes.SHA_384.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha384Hex_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_384.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha512Hex() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "4dbff86cc2ca1bae1e16468a05cb9881c97f1753bce3619034898faa1aabe429955a1bf8ec483d7421fe3c1646613a59ed5441fb0f321389f77f48a879c7b1f1";
		
		final String hash = GGHashes.SHA_512.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha512Hex_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_512.hashToHexString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}
	
    @Test
    public void sha256Base64() {

        final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

        final String expectedHash = "ccSA35PWri8e+tFEfGbJUl4xYhjPUfyNntgy8trxi3M=";
        
        final String hash = GGHashes.SHA_256.hashToBase64String(testBytes);
        
        assertThat(hash, equalTo( expectedHash ));
    }

	@Test
	public void sha256Base64Url() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "ccSA35PWri8e-tFEfGbJUl4xYhjPUfyNntgy8trxi3M=";
		
		final String hash = GGHashes.SHA_256.hashToBase64UrlString(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha256Base64_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_256.hashToBase64String(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha384Base64() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "/rZzSd89tvWSSBXWw9wTPwkYCSE3Mf5ce19JmeRjR5/yh39fKTb6Y7tDeEsS8+u0";
		
		final String hash = GGHashes.SHA_384.hashToBase64String(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

    @Test
    public void sha384Base64Url() {

        final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

        final String expectedHash = "_rZzSd89tvWSSBXWw9wTPwkYCSE3Mf5ce19JmeRjR5_yh39fKTb6Y7tDeEsS8-u0";
        
        final String hash = GGHashes.SHA_384.hashToBase64UrlString(testBytes);
        
        assertThat(hash, equalTo( expectedHash ));
    }

	@Test
	public void sha384Base64_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_384.hashToBase64String(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

	@Test
	public void sha512Base64() {

		final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

		final String expectedHash = "Tb/4bMLKG64eFkaKBcuYgcl/F1O842GQNImPqhqr5CmVWhv47Eg9dCH+PBZGYTpZ7VRB+w8yE4n3f0ioecex8Q==";
		
		final String hash = GGHashes.SHA_512.hashToBase64String(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}

    @Test
    public void sha512Base64Url() {

        final byte[] testBytes = GGStrings.toBytes("abcdefghijklmnopqrstuvwxyz");

        final String expectedHash = "Tb_4bMLKG64eFkaKBcuYgcl_F1O842GQNImPqhqr5CmVWhv47Eg9dCH-PBZGYTpZ7VRB-w8yE4n3f0ioecex8Q==";
        
        final String hash = GGHashes.SHA_512.hashToBase64UrlString(testBytes);
        
        assertThat(hash, equalTo( expectedHash ));
    }

	@Test
	public void sha512Base64_null() {

		final byte[] testBytes = null;

		final String expectedHash = null;
		
		final String hash = GGHashes.SHA_512.hashToBase64String(testBytes);
		
		assertThat(hash, equalTo( expectedHash ));
	}



}

