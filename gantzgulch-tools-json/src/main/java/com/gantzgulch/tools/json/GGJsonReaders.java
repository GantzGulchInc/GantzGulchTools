package com.gantzgulch.tools.json;

import com.fasterxml.jackson.databind.InjectableValues;
import com.gantzgulch.tools.json.impl.GGJsonReaderImpl;

public final class GGJsonReaders {

    public static GGJsonReader STRICT = new GGJsonReaderImpl(false, false);
    
    public static GGJsonReader STRICT_ISO8601 = new GGJsonReaderImpl(false, true);
    
    public static GGJsonReader LOOSE = new GGJsonReaderImpl(true, false);
    
    public static GGJsonReader LOOSE_ISO8601 = new GGJsonReaderImpl(true, true);

    public static GGJsonReader create(final boolean allowUncleanJson, final boolean useISO8601Dates, final InjectableValues injectableValues) {
        return new GGJsonReaderImpl(allowUncleanJson, useISO8601Dates, injectableValues);
    }
}
