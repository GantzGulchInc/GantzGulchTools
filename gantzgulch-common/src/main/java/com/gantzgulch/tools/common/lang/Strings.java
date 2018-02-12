package com.gantzgulch.tools.common.lang;

public final class Strings {

	private Strings() {
		throw new UnsupportedOperationException();
	}
	
	public static String repeat(final String value, final int count){
	
		if( value == null ){
			return null;
		}
		
		if( count < 1 ){
			return "";
		}
		
		if( count == 1 ){
			return value;
		}
		
		final StringBuilder sb = new StringBuilder( value.length() * count);

		for(int index = 0; index<count; index++){
			sb.append(value);
		}
		
		return sb.toString();
	}
	
	public static String leftPad(final String value, final int width) {
		
		if( value == null ){
			return null;
		}
		
		int toAdd = width - value.length();
		
		if( toAdd < 1 ){
			return value;
		}
		
		return repeat(" ", toAdd) + value;
	}
	
	public static String rightPad(final String value, final int width) {
		
		if( value == null ){
			return null;
		}
		
		int toAdd = width - value.length();
		
		if( toAdd < 1 ){
			return value;
		}
		
		return value + repeat(" ", toAdd);
	}
	
	public static String center(final String value, final int width) {
		
		if( value == null ){
			return null;
		}
		
		if( width < 1 || width < value.length() ){
			return value;
		}
		
		final int strLen = value.length();
        final int pads = width - strLen;

        if (pads <= 0) {
            return value;
        }
        
        return rightPad(leftPad(value, strLen + pads / 2), width);
		
	}
}
