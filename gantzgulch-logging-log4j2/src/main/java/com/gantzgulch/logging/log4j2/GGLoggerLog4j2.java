package com.gantzgulch.logging.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.logging.core.GGLoggerUtil;

/**
 * A log wrapper with {@code String.format} capabilities.
 * 
 * @author gantzm
 *
 */
public class GGLoggerLog4j2 implements GGLogger {

	private final Logger log;

	public GGLoggerLog4j2(final String logClazz) {
		this.log = LogManager.getLogger(logClazz);
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
		return log.isFatalEnabled();
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

			log.info("+" + GGLoggerUtil.repeat("-", width) + "+");
			log.info("|" + GGLoggerUtil.repeat(" ", width) + "|");
			log.info("|" + GGLoggerUtil.repeat(" ", width) + "|");

			for (final Object msg : messages) {
				log.info("|" + GGLoggerUtil.center(msg.toString(), width) + "|");
			}

			log.info("|" + GGLoggerUtil.repeat(" ", width) + "|");
			log.info("+" + GGLoggerUtil.repeat("-", width) + "+");
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
		log.fatal(message);
	}

	@Override
	public void fatal(final String message, final Object... args) {

		if (isFatalEnabled()) {
			log.fatal(format(message, args));
		}
	}

	@Override
	public void fatal(final Throwable t, final String message) {
		log.fatal(message, t);
	}

	@Override
	public void fatal(final Throwable t, final String message, final Object... args) {

		if (isFatalEnabled()) {
			log.fatal(format(message, args), t);
		}
	}

	private String format(final String message, Object... args) {
		return message != null ? String.format(message, args) : "";
	}

}
