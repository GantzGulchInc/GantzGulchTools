package com.gantzgulch.tools.common.codec;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.gantzgulch.tools.common.codec.GGHex;

public class GGHexTest {

	@Test
	public void toHexStringFromByte() {
		
		assertThat(GGHex.toHexString( (byte) 0), equalTo("00") );
		assertThat(GGHex.toHexString( (byte) 1), equalTo("01") );
		assertThat(GGHex.toHexString( (byte) 2), equalTo("02") );
		assertThat(GGHex.toHexString( (byte) 0x0F), equalTo("0f") );
		assertThat(GGHex.toHexString( (byte) 0x10), equalTo("10") );
		assertThat(GGHex.toHexString( (byte) 0x1F), equalTo("1f") );
		assertThat(GGHex.toHexString( (byte) 0x20), equalTo("20") );
		assertThat(GGHex.toHexString( (byte) 0x80), equalTo("80") );
		assertThat(GGHex.toHexString( (byte) 0x8F), equalTo("8f") );
		assertThat(GGHex.toHexString( (byte) 0xaF), equalTo("af") );
		assertThat(GGHex.toHexString( (byte) 0xcF), equalTo("cf") );
		assertThat(GGHex.toHexString( (byte) 0xdF), equalTo("df") );
		assertThat(GGHex.toHexString( (byte) 0xFF), equalTo("ff") );
		
	}

	@Test
	public void toHexStringFromString() {
		
		final String TEST_STRING = "\u0004 !\"#$%&'()*+,0123456789ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz";
		final String TEST_HEX = "04202122232425262728292a2b2c303132333435363738394142434445464748494a4b4c4d4e4f5051525354555657595a6162636465666768696a6b6c6d6e6f707172737475767778797a";
		
		assertThat(GGHex.toHexString(TEST_STRING), equalTo(TEST_HEX) );
		assertThat(GGHex.fromHexStringToString( TEST_HEX), equalTo(TEST_STRING) );
		
	}
}
