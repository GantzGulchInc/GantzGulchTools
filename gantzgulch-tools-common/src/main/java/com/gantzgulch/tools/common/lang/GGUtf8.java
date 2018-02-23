package com.gantzgulch.tools.common.lang;

import java.nio.charset.Charset;

public final class GGUtf8 {

    public static final String NAME = "UTF-8";

    public static final Charset CHARSET = Charset.forName(NAME);

    private GGUtf8() {
        throw new UnsupportedOperationException();
    }

}
