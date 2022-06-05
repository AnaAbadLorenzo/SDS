package com.sds.service.common;

import org.apache.commons.lang3.StringUtils;

public class CommonUtilities {

	public static String coalesce(final String one, final String two) {
		if (one != null || StringUtils.EMPTY.equals(one)) {
			return one;
		} else {
			return two;
		}
	}

	public static int countDigit(final int x) {
		return (int) Math.floor(Math.log10(x) + 1);
	}
}
