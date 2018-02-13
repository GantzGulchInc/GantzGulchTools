package com.gantzgulch.tools.common.logging;

/**
 * A log wrapper with {@code String.format} capabilities.
 * 
 * @author gantzm
 *
 */
public interface GGLogger {

	public static GGLogger getLogger(final String name) {
		return GGLoggerCache.INSTANCE.getLogger(name);
	}

	public static GGLogger getLogger(final Class<?> className) {
		return GGLoggerCache.INSTANCE.getLogger(className);
	}

	boolean isTraceEnabled();

	boolean isDebugEnabled();

	boolean isInfoEnabled();

	boolean isWarnEnabled();

	boolean isErrorEnabled();

	boolean isFatalEnabled();

	void trace(final String message);

	void trace(final String message, final Object... args);

	void trace(final Throwable t, final String message);

	void trace(final Throwable t, final String message, final Object... args);

	void debug(final String message);

	void debug(final String message, final Object... args);

	void debug(final Throwable t, final String message);

	void debug(final Throwable t, final String message, final Object... args);

	void info(final String message);

	void info(final String message, final Object... args);

	void info(final Throwable t, final String message);

	void info(final Throwable t, final String message, final Object... args);

	void infoBox(final Object... messages);

	void warn(final String message);

	void warn(final String message, final Object... args);

	void warn(final Throwable t, final String message);

	void warn(final Throwable t, final String message, final Object... args);

	void error(final String message);

	void error(final String message, final Object... args);

	void error(final Throwable t, final String message);

	void error(final Throwable t, final String message, final Object... args);

	void fatal(final String message);

	void fatal(final String message, final Object... args);

	void fatal(final Throwable t, final String message);

	void fatal(final Throwable t, final String message, final Object... args);

}
