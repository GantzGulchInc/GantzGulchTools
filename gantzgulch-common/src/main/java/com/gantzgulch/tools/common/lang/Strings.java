package com.gantzgulch.tools.common.lang;

/**
 * This class consists of {@code static} utility methods for operating
 * on Strings.
 * 
 * @author gantzm
 *
 */
public final class Strings {

	private Strings() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Repeat a String {@code repeat} times to form a new String.
     * 
	 * @param string the String to repeat.
	 * @param repeat the number times to repeat the string.
	 * 
	 * @return the repeated String.
	 */
	public static String repeat(final String string, final int repeat){
	
		if( string == null ){
			return null;
		}
		
		if( repeat < 1 ){
			return "";
		}
		
		if( repeat == 1 ){
			return string;
		}
		
		final StringBuilder sb = new StringBuilder( string.length() * repeat);

		for(int index = 0; index<repeat; index++){
			sb.append(string);
		}
		
		return sb.toString();
	}
	
	/**
	 * Left pad a String with spaces to length {@code width}.
	 * 
	 * @param string the String to pad.
	 * @param width the total width to pad to.
	 * 
	 * @return the padded String.
	 */
	public static String leftPad(final String string, final int width) {
		
		if( string == null ){
			return null;
		}
		
		int toAdd = width - string.length();
		
		if( toAdd < 1 ){
			return string;
		}
		
		return repeat(" ", toAdd) + string;
	}
	
	/**
	 * Right pad a String with spaces to length {@code width}.
	 * 
	 * @param string the String to pad.
	 * @param width the total width to pad to.
	 * 
	 * @return the padded String.
	 */
	public static String rightPad(final String string, final int width) {
		
		if( string == null ){
			return null;
		}
		
		int toAdd = width - string.length();
		
		if( toAdd < 1 ){
			return string;
		}
		
		return string + repeat(" ", toAdd);
	}
	
	/**
	 * Center a String with spaces to {@code width}.
	 * 
	 * @param string the String to center.
	 * @param width the total width to center to.
	 * 
	 * @return the centered String.
	 */
	public static String center(final String string, final int width) {
		
		if( string == null ){
			return null;
		}
		
		if( width < 1 || width < string.length() ){
			return string;
		}
		
		final int strLen = string.length();
        final int pads = width - strLen;

        if (pads <= 0) {
            return string;
        }
        
        return rightPad(leftPad(string, strLen + pads / 2), width);
		
	}
}
