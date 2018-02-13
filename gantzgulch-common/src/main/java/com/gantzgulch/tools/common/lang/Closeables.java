package com.gantzgulch.tools.common.lang;

import java.io.Closeable;
import java.io.IOException;

/**
 * This class consists of {@code static} utility methods for operating
 * on closeables without throwing exceptions.
 * 
 * @author gantzm
 *
 */
public final class Closeables {

	private Closeables() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Quietly close a single closeable.
	 * 
	 * Warning: This should not be used on writable closeables as corruption could occur.
	 * 
	 * @param closeable the closeable to be closed.
	 */
	public static void closeQuietly(final Closeable closeable) {

		if (closeable != null) {
			try {
				closeable.close();
			} catch (final RuntimeException | IOException e) {
				// Do nothing! That's the entire point!
			}
		}

	}

	/**
	 * Quietly close multiple closeables.
	 * 
	 * Warning: This should not be used on writable closeables as corruption could occur.
	 * 
	 * @param closeables the closeables to be closed.
	 */
	public static void closeQuietly(final Closeable... closeables) {

		if (closeables != null) {
			for (final Closeable c : closeables) {
				closeQuietly(c);
			}
		}
	}

}
