package com.gantzgulch.logging.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.gantzgulch.logging.core.impl.GGAbstractLogger;

/**
 * A log wrapper with {@code String.format} capabilities.
 * 
 * @author gantzm
 *
 */
public class GGLoggerSLF4J extends GGAbstractLogger {

    private final Logger log;

    public GGLoggerSLF4J(final String logClazz) {
        this.log = LoggerFactory.getLogger(logClazz);
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public void trace(final String message) {
        log.trace(message);
    }

    @Override
    public void trace(final Throwable t, final String message) {
        log.trace(message, t);
    }

    @Override
    public void debug(final String message) {
        log.debug(message);
    }

    @Override
    public void debug(final Throwable t, final String message) {
        log.debug(message, t);
    }

    @Override
    public void info(final String message) {
        log.info(message);
    }

    @Override
    public void info(final Throwable t, final String message) {
        log.info(message, t);
    }

    @Override
    public void warn(final String message) {
        log.warn(message);
    }

    @Override
    public void warn(final Throwable t, final String message) {
        log.warn(message, t);
    }

    @Override
    public void error(final String message) {
        log.error(message);
    }

    @Override
    public void error(final Throwable t, final String message) {
        log.error(message, t);
    }

    @Override
    public void fatal(final String message) {
        log.error(message);
    }

    @Override
    public void fatal(final Throwable t, final String message) {
        log.error(message, t);
    }

}
