package com.gantzgulch.logging.core.fallback;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import com.gantzgulch.logging.core.impl.GGAbstractLogger;

public class GGLoggerFallback extends GGAbstractLogger {

    private final String name;

    public GGLoggerFallback(final String name) {
        this.name = name;
    }
    
    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isFatalEnabled() {
        return true;
    }

    @Override
    public void trace(final String message) {
        log("TRACE", null, message);
    }

    @Override
    public void trace(final Throwable t, final String message) {
        log("TRACE", t, message);
    }

    @Override
    public void debug(final String message) {
        log("DEBUG", null, message);
    }

    @Override
    public void debug(final Throwable t, final String message) {
        log("DEBUG", t, message);
    }

    @Override
    public void info(final String message) {
        log("INFO", null, message);
    }

    @Override
    public void info(final Throwable t, final String message) {
        log("INFO", t, message);
    }

    @Override
    public void warn(final String message) {
        log("WARN", null, message);
    }

    @Override
    public void warn(final Throwable t, final String message) {
        log("WARN", t, message);
    }

    @Override
    public void error(final String message) {
        log("ERROR", null, message);
    }

    @Override
    public void error(final Throwable t, final String message) {
        log("ERROR", t, message);
    }

    @Override
    public void fatal(final String message) {
        log("FATAL", null, message);
    }

    @Override
    public void fatal(final Throwable t, final String message) {
        log("FATAL", t, message);
    }

    private void log(final String level, final Throwable t, final String message) {
        
        final Date now = new Date();
        
        final String stackTrace = createStackTrace(t);
        
        System.out.println(String.format("%s %s %s %s%s", now, level, name, message, stackTrace));
        
    }

    private String createStackTrace(Throwable t) {
        
        if( t == null ) {
            return "";
        }
        
        final StringWriter sw = new StringWriter();
        
        final PrintWriter pw = new PrintWriter(sw);
        
        pw.println(pw);
        
        t.printStackTrace(pw);
        
        pw.flush();
        
        sw.flush();
        
        return sw.toString();
    }

}
