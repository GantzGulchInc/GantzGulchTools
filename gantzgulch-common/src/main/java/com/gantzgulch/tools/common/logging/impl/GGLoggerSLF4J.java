package com.gantzgulch.tools.common.logging.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gantzgulch.tools.common.lang.GGStrings;
import com.gantzgulch.tools.common.logging.GGLogger;

/**
 * A log wrapper with {@code String.format} capabilities.
 * 
 * @author gantzm
 *
 */
public class GGLoggerSLF4J implements GGLogger {

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
	public void trace(final String message, final Object... args) {

		if (isTraceEnabled()) {
			log.trace(format(message, args));
		}
	}

	@Override
	public void trace(final Throwable t, final String message) {
		log.trace(message, t);
	}

	@Override
	public void trace(final Throwable t, final String message, final Object... args) {

		if (isTraceEnabled()) {
			log.trace(format(message, args), t);
		}
	}

	@Override
	public void debug(final String message) {

		log.debug(message);
	}

	@Override
	public void debug(final String message, final Object... args) {

		if (isDebugEnabled()) {
			log.debug(format(message, args));
		}
	}

	@Override
	public void debug(final Throwable t, final String message) {
		log.debug(message, t);
	}

	@Override
	public void debug(final Throwable t, final String message, final Object... args) {

		if (isDebugEnabled()) {
			log.debug(format(message, args), t);
		}
	}

	@Override
	public void info(final String message) {
		log.info(message);
	}

	@Override
	public void info(final String message, final Object... args) {

		if (isInfoEnabled()) {
			log.info(format(message, args));
		}
	}

	@Override
	public void info(final Throwable t, final String message) {
		log.info(message, t);
	}

	@Override
	public void info(final Throwable t, final String message, final Object... args) {

		if (isInfoEnabled()) {
			log.info(format(message, args), t);
		}
	}

	@Override
	public void infoBox(final Object... messages) {

		if (isInfoEnabled()) {

			int width = 120;

			for (final Object message : messages) {
				width = Math.max(width, message.toString().length());
			}

			width = width + 20;

			log.info("+" + GGStrings.repeat("-", width) + "+");
			log.info("|" + GGStrings.repeat(" ", width) + "|");
			log.info("|" + GGStrings.repeat(" ", width) + "|");

			for (final Object msg : messages) {
				log.info("|" + GGStrings.center(msg.toString(), width) + "|");
			}

			log.info("|" + GGStrings.repeat(" ", width) + "|");
			log.info("+" + GGStrings.repeat("-", width) + "+");
		}
	}

	@Override
	public void warn(final String message) {
		log.warn(message);
	}

	@Override
	public void warn(final String message, final Object... args) {

		if (isWarnEnabled()) {
			log.warn(format(message, args));
		}
	}

	@Override
	public void warn(final Throwable t, final String message) {
		log.warn(message, t);
	}

	@Override
	public void warn(final Throwable t, final String message, final Object... args) {

		if (isWarnEnabled()) {
			log.warn(format(message, args), t);
		}
	}

	@Override
	public void error(final String message) {
		log.error(message);
	}

	@Override
	public void error(final String message, final Object... args) {

		if (isErrorEnabled()) {
			log.error(format(message, args));
		}
	}

	@Override
	public void error(final Throwable t, final String message) {
		log.error(message, t);
	}

	@Override
	public void error(final Throwable t, final String message, final Object... args) {

		if (isErrorEnabled()) {
			log.error(format(message, args), t);
		}
	}

	@Override
	public void fatal(final String message) {
		log.error(message);
	}

	@Override
	public void fatal(final String message, final Object... args) {

		if (isFatalEnabled()) {
			log.error(format(message, args));
		}
	}

	@Override
	public void fatal(final Throwable t, final String message) {
		log.error(message, t);
	}

	@Override
	public void fatal(final Throwable t, final String message, final Object... args) {

		if (isFatalEnabled()) {
			log.error(format(message, args), t);
		}
	}

	private String format(final String message, Object... args) {
		return message != null ? String.format(message, args) : "";
	}

}
