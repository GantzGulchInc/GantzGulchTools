package com.gantzgulch.tools.common.lang;

/**
 * This class consists of a {@code static} utility method for sleeping without throwing exceptions.
 * 
 * @author gantzm
 *
 */
public final class Sleep {

	private Sleep() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sleep for millis ignoring exceptions.
	 * 
	 * @param millis time in millis to sleep for.
	 */
	public static void sleep(final long millis) {

		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			// Do nothing.
		}
	}
}
