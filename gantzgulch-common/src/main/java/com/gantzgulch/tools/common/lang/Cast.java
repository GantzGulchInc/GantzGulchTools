package com.gantzgulch.tools.common.lang;

public final class Cast {

	private Cast() {
		throw new UnsupportedOperationException();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T cast(final Object object){
		return (T)object;
	}
}
