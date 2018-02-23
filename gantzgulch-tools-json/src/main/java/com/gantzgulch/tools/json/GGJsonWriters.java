package com.gantzgulch.tools.json;

import com.gantzgulch.tools.json.impl.GGJsonWriterImpl;

public final class GGJsonWriters {

    public static GGJsonWriter STRICT = new GGJsonWriterImpl(false, false);
    
    public static GGJsonWriter STRICT_ISO8601 = new GGJsonWriterImpl(false, true);
    
    public static GGJsonWriter PRETTY = new GGJsonWriterImpl(true, false);
    
    public static GGJsonWriter PRETTY_IOS8601 = new GGJsonWriterImpl(true, true);
    
}
