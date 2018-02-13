package com.gantzgulch.tools.common.lang;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StringsTest {

	
	@Test
	public void repeat() {
		
		assertThat(Strings.repeat(null, -100), equalTo(null));
		assertThat(Strings.repeat(null, -1), equalTo(null));
		assertThat(Strings.repeat(null, 0), equalTo(null));
		assertThat(Strings.repeat(null, 1), equalTo(null));
		assertThat(Strings.repeat(null, 100), equalTo(null));
		
		assertThat(Strings.repeat("a", -100), equalTo(""));
		assertThat(Strings.repeat("a", -1), equalTo(""));
		assertThat(Strings.repeat("a", 0), equalTo(""));
		assertThat(Strings.repeat("a", 1), equalTo("a"));
		assertThat(Strings.repeat("a", 100), equalTo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		
		assertThat(Strings.repeat("Car", -100), equalTo(""));
		assertThat(Strings.repeat("Car", -1), equalTo(""));
		assertThat(Strings.repeat("Car", 0), equalTo(""));
		assertThat(Strings.repeat("Car", 1), equalTo("Car"));
		assertThat(Strings.repeat("Car", 100), equalTo("CarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCarCar"));
		
	}
	
	@Test
	public void leftPad() {
		
		assertThat(Strings.leftPad(null, -100), equalTo(null));
		assertThat(Strings.leftPad(null, -1), equalTo(null));
		assertThat(Strings.leftPad(null, 0), equalTo(null));
		assertThat(Strings.leftPad(null, 1), equalTo(null));
		assertThat(Strings.leftPad(null, 100), equalTo(null));
		
		assertThat(Strings.leftPad("a", -100), equalTo("a"));
		assertThat(Strings.leftPad("a", -1), equalTo("a"));
		assertThat(Strings.leftPad("a", 0), equalTo("a"));
		assertThat(Strings.leftPad("a", 1), equalTo("a"));
		assertThat(Strings.leftPad("a", 5), equalTo("    a"));
		assertThat(Strings.leftPad("a", 100), equalTo("                                                                                                   a"));
		
		assertThat(Strings.leftPad("Car", -100), equalTo("Car"));
		assertThat(Strings.leftPad("Car", -1), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 0), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 1), equalTo("Car"));
		assertThat(Strings.leftPad("Car", 7), equalTo("    Car"));
		assertThat(Strings.leftPad("Car", 100), equalTo("                                                                                                 Car"));
		
	}
	
	@Test
	public void rightPad() {
		
		assertThat(Strings.rightPad(null, -100), equalTo(null));
		assertThat(Strings.rightPad(null, -1), equalTo(null));
		assertThat(Strings.rightPad(null, 0), equalTo(null));
		assertThat(Strings.rightPad(null, 1), equalTo(null));
		assertThat(Strings.rightPad(null, 100), equalTo(null));
		
		assertThat(Strings.rightPad("a", -100), equalTo("a"));
		assertThat(Strings.rightPad("a", -1), equalTo("a"));
		assertThat(Strings.rightPad("a", 0), equalTo("a"));
		assertThat(Strings.rightPad("a", 1), equalTo("a"));
		assertThat(Strings.rightPad("a", 5), equalTo("a    "));
		assertThat(Strings.rightPad("a", 100), equalTo("a                                                                                                   "));
		
		assertThat(Strings.rightPad("Car", -100), equalTo("Car"));
		assertThat(Strings.rightPad("Car", -1), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 0), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 1), equalTo("Car"));
		assertThat(Strings.rightPad("Car", 7), equalTo("Car    "));
		assertThat(Strings.rightPad("Car", 100), equalTo("Car                                                                                                 "));
		
	}
	
	@Test
	public void center() {
		
		assertThat(Strings.center(null, -100), equalTo(null));
		assertThat(Strings.center(null, -1), equalTo(null));
		assertThat(Strings.center(null, 0), equalTo(null));
		assertThat(Strings.center(null, 1), equalTo(null));
		assertThat(Strings.center(null, 100), equalTo(null));
		
		assertThat(Strings.center("a", -100), equalTo("a"));
		assertThat(Strings.center("a", -1), equalTo("a"));
		assertThat(Strings.center("a", 0), equalTo("a"));
		assertThat(Strings.center("a", 1), equalTo("a"));
		assertThat(Strings.center("a", 5), equalTo("  a  "));
		assertThat(Strings.center("a", 100), equalTo("                                                 a                                                  "));
		
		assertThat(Strings.center("Car", -100), equalTo("Car"));
		assertThat(Strings.center("Car", -1), equalTo("Car"));
		assertThat(Strings.center("Car", 0), equalTo("Car"));
		assertThat(Strings.center("Car", 1), equalTo("Car"));
		assertThat(Strings.center("Car", 7), equalTo("  Car  "));
		assertThat(Strings.center("Car", 100), equalTo("                                                Car                                                 "));
		
	}

	@Test
	public void toHexByte() {
		
		assertThat(Strings.toHex( (byte) 0), equalTo("00") );
		assertThat(Strings.toHex( (byte) 1), equalTo("01") );
		assertThat(Strings.toHex( (byte) 2), equalTo("02") );
		assertThat(Strings.toHex( (byte) 0x0F), equalTo("0f") );
		assertThat(Strings.toHex( (byte) 0x10), equalTo("10") );
		assertThat(Strings.toHex( (byte) 0x1F), equalTo("1f") );
		assertThat(Strings.toHex( (byte) 0x20), equalTo("20") );
		assertThat(Strings.toHex( (byte) 0x80), equalTo("80") );
		assertThat(Strings.toHex( (byte) 0x8F), equalTo("8f") );
		assertThat(Strings.toHex( (byte) 0xaF), equalTo("af") );
		assertThat(Strings.toHex( (byte) 0xcF), equalTo("cf") );
		assertThat(Strings.toHex( (byte) 0xdF), equalTo("df") );
		assertThat(Strings.toHex( (byte) 0xFF), equalTo("ff") );
		
	}

	@Test
	public void toHexString() {
		
		final String TEST_STRING = "\u0004 !\"#$%&'()*+,0123456789ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwxyz";
		final String TEST_HEX = "04202122232425262728292a2b2c303132333435363738394142434445464748494a4b4c4d4e4f5051525354555657595a6162636465666768696a6b6c6d6e6f707172737475767778797a";
		
		assertThat(Strings.toHex(TEST_STRING), equalTo(TEST_HEX) );
		assertThat(Strings.fromHex( TEST_HEX), equalTo(TEST_STRING) );
		
	}
}
