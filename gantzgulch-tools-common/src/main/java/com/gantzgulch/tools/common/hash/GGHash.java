package com.gantzgulch.tools.common.hash;

public interface GGHash {

    byte[] hash(final byte[] input);

    String hashToHexString(final byte[] input);

    String hashToBase64String(final byte[] input);

    String hashToBase64UrlString(final byte[] input);

}