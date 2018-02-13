package com.gantzgulch.tools.common.lang;

/**
 * This class consists of {@code static} utility methods for operating on
 * Strings.
 * 
 * @author gantzm
 *
 */
public final class Bytes {

	public static final int HEX_RADIX = 16;
	
	public static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private Bytes() {
		throw new UnsupportedOperationException();
	}

	public static byte[] fromHex(final String hex) {

		if (hex == null) {
			return null;
		}

		if ((hex.length() & 0x01) != 0) {
			throw new IllegalArgumentException("Must contain even number of hex digits.");
		}

		return fromHex(hex.toCharArray());
	}

	public static byte[] fromHex(final char[] chars) {

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

		return bytes;

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

	public static String toHex(final byte[] bytes) {

		if (bytes == null) {
			return null;
		}

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
