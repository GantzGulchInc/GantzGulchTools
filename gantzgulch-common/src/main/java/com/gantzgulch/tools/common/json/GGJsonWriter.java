package com.gantzgulch.tools.common.json;

import com.gantzgulch.tools.common.json.impl.GGJsonWriterImpl;

public interface GGJsonWriter {

    public static GGJsonWriter STRICT = new GGJsonWriterImpl(false, false, false);
    
    public static GGJsonWriter STRICT_MILLIS = new GGJsonWriterImpl(false, false, true);
    
    public static GGJsonWriter STRICT_PRETTY = new GGJsonWriterImpl(false, true, false);
    
    public static GGJsonWriter LOOSE = new GGJsonWriterImpl(true, false, false);
    
    public static GGJsonWriter LOOSE_PRETTY = new GGJsonWriterImpl(true, true, false);
    
    String writeAsString(final Object value);

}
