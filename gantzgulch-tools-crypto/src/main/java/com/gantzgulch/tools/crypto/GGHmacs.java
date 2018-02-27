package com.gantzgulch.tools.crypto;

import com.gantzgulch.tools.crypto.alg.mac.GGHmacImpl;

public final class GGHmacs {

    public static final GGHmac MD5 = GGHmacImpl.MD5;
    public static final GGHmac SHA_1 = GGHmacImpl.SHA_1;
    public static final GGHmac SHA_224 = GGHmacImpl.SHA_224;
    public static final GGHmac SHA_256 = GGHmacImpl.SHA_256;
    public static final GGHmac SHA_384 = GGHmacImpl.SHA_384;
    public static final GGHmac SHA_512 = GGHmacImpl.SHA_512;

}
