package com.gantzgulch.tools.common.codec;

import com.gantzgulch.tools.common.lang.GGStrings;

public final class GGHex {

    private static final int HEX_RADIX = 16;

    private GGHex() {
        throw new UnsupportedOperationException();
    }

    public static String toHexString(final String string) {
    
        return toHexString( GGStrings.toBytes(string) );
    }
    
    public static String toHexString(final byte[] bytes) {

        if (bytes == null) {
            return null;
        }

        final int bytesLen = bytes.length;

        final StringBuilder sb = new StringBuilder(bytesLen * 2);

        for (int i = 0; i < bytesLen; i++) {

            final byte b = bytes[i];

            appendHexString(b, sb);
        }

        return sb.toString();
    }

    public static String toHexString(final byte b) {

        return "" + Character.forDigit(b >> 4 & 0x0f, HEX_RADIX) + Character.forDigit(b & 0x0f, HEX_RADIX);

    }
    
    public static String fromHexStringToString(final String hex) {
        
        return GGStrings.fromBytes(fromHexString(hex));
        
    }

    public static byte[] fromHexString(final String hex) {

        if (hex == null) {
            return null;
        }

        if ((hex.length() & 0x01) != 0) {
            throw new IllegalArgumentException("Must contain even number of hex digits.");
        }

        return fromHexCharArray(hex.toCharArray());
    }

    public static byte[] fromHexCharArray(final char[] chars) {

        if (chars == null) {
            return null;
        }

        if ((chars.length & 0x01) != 0) {
            throw new IllegalArgumentException("Must contain even number of hex digits.");
        }

        final byte[] bytes = new byte[chars.length >> 1];

        for (int output = 0, input = 0; input < chars.length; output++) {

            int value = fromHexCharacters(chars[input], chars[input+1]);
            
            input += 2;

            bytes[output] = (byte) (value & 0xFF);
        }

        return bytes;

    }


    private static void appendHexString(final byte b, final StringBuilder builder) {

        builder.append(Character.forDigit(b >> 4 & 0x0f, HEX_RADIX));
        builder.append(Character.forDigit(b & 0x0f, HEX_RADIX));

    }
    
    private static int fromHexCharacters(final char hi, final char low) {

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
}
