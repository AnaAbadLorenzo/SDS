package com.sds.controller.common;

public class CommonUtilities {

	public static <T> T coalesce(final T one, final T two) {
		return one != null ? one : two;
	}
}
