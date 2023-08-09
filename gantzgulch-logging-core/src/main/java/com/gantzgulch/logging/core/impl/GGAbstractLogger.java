package com.gantzgulch.logging.core.impl;

import com.gantzgulch.logging.core.GGLogger;

public abstract class GGAbstractLogger implements GGLogger {

    @Override
    public void trace(String message, Object... args) {
        if( isTraceEnabled() ) {
            trace( format(message,args));
        }
    }

    @Override
    public void trace(Throwable t, String message, Object... args) {
        if( isTraceEnabled() ) {
            trace( t, format(message,args));
        }
    }

    @Override
    public void debug(String message, Object... args) {
        if( isDebugEnabled() ) {
            debug( format(message, args) );
        }
    }

    @Override
    public void debug(Throwable t, String message, Object... args) {
        if( isDebugEnabled() ) {
            debug( t, format(message, args) );
        }
    }

    @Override
    public void info(String message, Object... args) {
        if( isInfoEnabled() ) {
            info( format(message,args));
        }
    }

    @Override
    public void info(Throwable t, String message, Object... args) {
        if( isInfoEnabled() ) {
            info( t, format(message,args));
        }
    }

    @Override
    public void infoBox(Object... messages) {
        
        if (isInfoEnabled()) {

            int width = 120;

            for (final Object message : messages) {
                width = Math.max(width, message.toString().length());
            }

            width = width + 2;

            info("+" + GGLoggerUtil.repeat("-", width) + "+");
            info("|" + GGLoggerUtil.repeat(" ", width) + "|");
            info("|" + GGLoggerUtil.repeat(" ", width) + "|");

            for (final Object msg : messages) {
                info("|" + GGLoggerUtil.center(msg.toString(), width) + "|");
            }

            info("|" + GGLoggerUtil.repeat(" ", width) + "|");
            info("+" + GGLoggerUtil.repeat("-", width) + "+");
        }
        
    }

    @Override
    public void warn(String message, Object... args) {
        if( isWarnEnabled() ) {
            warn(format(message,args));
        }
    }

    @Override
    public void warn(Throwable t, String message, Object... args) {
        if( isWarnEnabled() ) {
            warn(t, format(message,args));
        }
    }

    @Override
    public void error(String message, Object... args) {
        if( isErrorEnabled() ) {
            error(format(message,args));
        }
    }

    @Override
    public void error(Throwable t, String message, Object... args) {
        if( isErrorEnabled() ) {
            error(t, format(message,args));
        }
    }

    @Override
    public void fatal(String message, Object... args) {
        if( isFatalEnabled() ) {
            fatal(format(message,args));
        }
    }

    @Override
    public void fatal(Throwable t, String message, Object... args) {
        if( isFatalEnabled() ) {
            fatal(t, format(message,args));
        }
    }

    private String format(final String message, Object... args) {
        return message != null ? String.format(message, args) : "";
    }

}
