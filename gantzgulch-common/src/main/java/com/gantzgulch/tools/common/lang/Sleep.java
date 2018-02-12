package com.gantzgulch.tools.common.lang;

public final class Sleep {

	private Sleep() {
		throw new UnsupportedOperationException();
	}

	public static void sleep(final long millis) {

		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			// Do nothing.
		}
	}
}
