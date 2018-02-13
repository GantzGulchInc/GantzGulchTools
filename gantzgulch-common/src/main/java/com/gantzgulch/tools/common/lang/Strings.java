package com.gantzgulch.tools.common.lang;

import java.nio.charset.Charset;

/**
 * This class consists of {@code static} utility methods for operating on
 * Strings.
 * 
 * @author gantzm
 *
 */
public final class Strings {

	public static final int HEX_RADIX = 16;
	
	public static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static final String UTF8_CHARSET_NAME = "UTF-8";

	public static final Charset UTF8_CHARSET = Charset.forName(UTF8_CHARSET_NAME);

	private Strings() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Repeat a String {@code repeat} times to form a new String.
	 * 
	 * @param string
	 *            the String to repeat.
	 * @param repeat
	 *            the number times to repeat the string.
	 * 
	 * @return the repeated String.
	 */
	public static String repeat(final String string, final int repeat) {

		if (string == null) {
			return null;
		}

		if (repeat < 1) {
			return "";
		}

		if (repeat == 1) {
			return string;
		}

		final StringBuilder sb = new StringBuilder(string.length() * repeat);

		for (int index = 0; index < repeat; index++) {
			sb.append(string);
		}

		return sb.toString();
	}

	/**
	 * Left pad a String with spaces to length {@code width}.
	 * 
	 * @param string
	 *            the String to pad.
	 * @param width
	 *            the total width to pad to.
	 * 
	 * @return the padded String.
	 */
	public static String leftPad(final String string, final int width) {

		if (string == null) {
			return null;
		}

		int toAdd = width - string.length();

		if (toAdd < 1) {
			return string;
		}

		return repeat(" ", toAdd) + string;
	}

	/**
	 * Right pad a String with spaces to length {@code width}.
	 * 
	 * @param string
	 *            the String to pad.
	 * @param width
	 *            the total width to pad to.
	 * 
	 * @return the padded String.
	 */
	public static String rightPad(final String string, final int width) {

		if (string == null) {
			return null;
		}

		int toAdd = width - string.length();

		if (toAdd < 1) {
			return string;
		}

		return string + repeat(" ", toAdd);
	}

	/**
	 * Center a String with spaces to {@code width}.
	 * 
	 * @param string
	 *            the String to center.
	 * @param width
	 *            the total width to center to.
	 * 
	 * @return the centered String.
	 */
	public static String center(final String string, final int width) {

		if (string == null) {
			return null;
		}

		if (width < 1 || width < string.length()) {
			return string;
		}

		final int strLen = string.length();
		final int pads = width - strLen;

		if (pads <= 0) {
			return string;
		}

		return rightPad(leftPad(string, strLen + pads / 2), width);

	}

	public static String fromBytes(final byte[] bytes) {

		return bytes != null ? new String(bytes, UTF8_CHARSET) : null;

	}

	public static byte[] toBytes(final String string) {

		return string != null ? string.getBytes(UTF8_CHARSET) : null;

	}

	public static String fromHex(final String hex) {

		if (hex == null) {
			return null;
		}

		if ((hex.length() & 0x01) != 0) {
			throw new IllegalArgumentException("Must contain even number of hex digits.");
		}

		return fromHex(hex.toCharArray());
	}

	public static String fromHex(final char[] chars) {

		if (chars == null) {
			return null;
		}

		if ((chars.length & 0x01) != 0) {
			throw new IllegalArgumentException("Must contain even number of hex digits.");
		}

		final byte[] bytes = new byte[chars.length >> 1];

		for (int output = 0, input = 0; input < chars.length; output++) {

			int value = fromHex(chars[input], chars[input+1]);
			
			input += 2;

			bytes[output] = (byte) (value & 0xFF);
		}

		return fromBytes(bytes);

	}
	
	public static int fromHex(final char hi, final char low) {

		int hiValue = Character.digit(hi, HEX_RADIX);
		
		if( hiValue == -1 ) {
			throw new IllegalArgumentException("Not a hex digit: " + hi);
		}
		
		hiValue = hiValue << 4;
		
		int loValue = Character.digit(low,  HEX_RADIX);
		
		if( loValue == -1 ){
			throw new IllegalArgumentException("Not a hex digit: " + low);
		}
		
		loValue = loValue & 0x0F;
		
		return hiValue | loValue;
		
	}

	/**
	 * Convert a String to UTF-8 bytes, then encode the bytes in Hex.
	 * 
	 * @param string the input String.
	 * 
	 * @return a Hex string.
	 * 
	 */
	public static String toHex(final String string) {

		if (string == null) {
			return null;
		}

		final byte[] bytes = toBytes(string);

		final int bytesLen = bytes.length;

		final StringBuilder sb = new StringBuilder(bytesLen * 2);

		for (int i = 0; i < bytesLen; i++) {

			final byte b = bytes[i];

			sb.append(toHex(b));
		}

		return sb.toString();
	}

	public static String toHex(final byte b) {

		return "" + Character.forDigit( b >> 4 & 0x0f, HEX_RADIX) + Character.forDigit(b & 0x0f, HEX_RADIX);

	}
}
