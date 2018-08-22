package com.itemconfiguration.export.bilder;

import org.apache.commons.lang3.StringUtils;

public class BuilderUtils {
	public static String NULL = "NULL";
	public static String ESCAPE_SYMBOL = "E";
	public static String COMMA = ",";
	private static String REGEX_ESCAPE_SYMBOLS = "['\\\\]+";


	public static String escapeValue(String value) {
		if (value == null) {
			return NULL;
		}
		return getEscapeSymbol(value) + "'" + value + "'";
	}

	private static String getEscapeSymbol(String value) {
		return value.matches(REGEX_ESCAPE_SYMBOLS)
				? ESCAPE_SYMBOL
				: StringUtils.EMPTY;
	}

	public static String escapeWithComma(String value) {
		return escapeValue(value) + COMMA;
	}

	public static String escapeWithComma(Long value) {
		return value.toString() + COMMA;
	}

	public static String escapeWithComma(Boolean value) {
		return value.toString() + COMMA;
	}

}
