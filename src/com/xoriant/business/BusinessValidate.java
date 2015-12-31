package com.xoriant.business;

public class BusinessValidate {
	public static boolean businessIsNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
