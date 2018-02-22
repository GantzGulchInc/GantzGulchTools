package com.gantzgulch.tools.common.lang;

/**
 * Cast an object to what you need without warnings.
 * 
 * @author gantzm
 *
 */
public final class Cast {

	private Cast() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Cast an object.
	 * 
	 * @param object the object to be casted.
	 * @param <T> the type to be cast to.
	 *  
	 * @return the casted object.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cast(final Object object){
		return (T)object;
	}
}
