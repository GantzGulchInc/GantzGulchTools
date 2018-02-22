package com.gantzgulch.tools.json.impl;

import java.text.FieldPosition;
import java.util.Date;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

public class ISO8601WithMillisFormat extends ISO8601DateFormat {

    private static final long serialVersionUID = 3136029199078988682L;

    @Override
    public StringBuffer format(final Date date, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
        String value = ISO8601Utils.format(date, true); // "true" to include milliseconds
        toAppendTo.append(value);
        return toAppendTo;
    }
    
}
