package com.gantzgulch.tools.common.lang;

import java.util.concurrent.TimeUnit;

/**
 * This class consists of a {@code static} utility method for sleeping without throwing exceptions.
 * 
 * @author gantzm
 *
 */
public final class GGSleep {

	private GGSleep() {
		throw new UnsupportedOperationException();
	}

	public static void sleep(final long duration, final TimeUnit unit) {
	    
	    sleep(unit.toMillis(duration));
	    
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
