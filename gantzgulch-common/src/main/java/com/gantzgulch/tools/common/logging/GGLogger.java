package com.gantzgulch.tools.common.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gantzgulch.tools.common.lang.Strings;

/**
 * A log wrapper with {@code String.format} capabilities.
 * 
 * @author gantzm
 *
 */
public class GGLogger {

	public static GGLogger getLogger(final String name) {
		return GGLoggerCache.INSTANCE.getLogger(name);
	}

	public static GGLogger getLogger(final Class<?> className) {
		return GGLoggerCache.INSTANCE.getLogger(className);
	}

	private final Log log;

	protected GGLogger(final String logClazz) {
		this.log = LogFactory.getLog(logClazz);
	}

	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	public boolean isFatalEnabled() {
		return log.isFatalEnabled();
	}

	public void trace(final String message) {

		log.trace(message);

	}

	public void trace(final String message, final Object... args) {

		if (isTraceEnabled()) {
			trace(format(message, args));
		}
	}

	public void trace(final Throwable t, final String message) {

		log.trace(message, t);

	}

	public void trace(final Throwable t, final String message, final Object... args) {

		if (isTraceEnabled()) {
			trace(t, format(message, args));
		}
	}

	public void debug(final String message) {

		log.debug(message);

	}

	public void debug(final String message, final Object... args) {

		if (isDebugEnabled()) {
			debug(format(message, args));
		}
	}

	public void debug(final Throwable t, final String message) {

		log.debug(message, t);
	}

	public void debug(final Throwable t, final String message, final Object... args) {

		if (isDebugEnabled()) {
			log.debug(format(message, args), t);
		}
	}

	public void info(final String message) {

		log.info(message);

	}

	public void info(final String message, final Object... args) {

		if (isInfoEnabled()) {
			info(format(message, args));
		}
	}

	public void info(final Throwable t, final String message) {

		log.info(message, t);

	}

	public void info(final Throwable t, final String message, final Object... args) {

		if (isInfoEnabled()) {
			info(t, format(message, args));
		}
	}

	public void infoBox(final Object... messages) {

		if (isInfoEnabled()) {

			int width = 120;

			for (final Object message : messages) {
				width = Math.max(width, message.toString().length());
			}

			width = width + 20;

			info("+" + Strings.repeat("-", width) + "+");
			info("|" + Strings.repeat(" ", width) + "|");
			info("|" + Strings.repeat(" ", width) + "|");

			for (final Object msg : messages) {
				info("|" + Strings.center(msg.toString(), width) + "|");
			}

			info("|" + Strings.repeat(" ", width) + "|");
			info("+" + Strings.repeat("-", width) + "+");
		}
	}

	public void warn(final String message) {

		log.warn(message);

	}

	public void warn(final String message, final Object... args) {

		if (isWarnEnabled()) {
			warn(format(message, args));
		}
	}

	public void warn(final Throwable t, final String message) {

		log.warn(message, t);

	}

	public void warn(final Throwable t, final String message, final Object... args) {

		if (isWarnEnabled()) {
			warn(t, format(message, args));
		}
	}

	public void error(final String message) {

		log.error(message);

	}

	public void error(final String message, final Object... args) {

		if (isErrorEnabled()) {
			error(format(message, args));
		}
	}

	public void error(final Throwable t, final String message) {

		log.error(message, t);
	}

	public void error(final Throwable t, final String message, final Object... args) {

		if (isErrorEnabled()) {
			error(t, format(message, args));
		}
	}

	public void fatal(final String message) {

		log.fatal(message);

	}

	public void fatal(final String message, final Object... args) {

		if (isFatalEnabled()) {
			fatal(format(message, args));
		}
	}

	public void fatal(final Throwable t, final String message) {

		log.fatal(message, t);
	}

	public void fatal(final Throwable t, final String message, final Object... args) {

		if (isFatalEnabled()) {
			fatal(t, format(message, args));
		}
	}

	private String format(final String message, Object... args) {
		return message != null ? String.format(message, args) : "";
	}

}
