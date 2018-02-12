package com.gantzgulch.tools.common.lang;

import java.io.Closeable;
import java.io.IOException;

public final class Closeables {

	private Closeables() {
		throw new UnsupportedOperationException();
	}

	public static void closeQuietly(final Closeable closeable) {

		if (closeable != null) {
			try {
				closeable.close();
			} catch (final RuntimeException | IOException e) {
				// Do nothing! That's the entire point!
			}
		}

	}

	public static void closeQuietly(final Closeable... closeables) {

		if (closeables != null) {
			for (final Closeable c : closeables) {
				closeQuietly(c);
			}
		}
	}

}
