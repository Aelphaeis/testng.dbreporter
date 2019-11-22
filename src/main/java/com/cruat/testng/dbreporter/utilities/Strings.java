package com.cruat.testng.dbreporter.utilities;

public class Strings {
	
	public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
        	 for (int i = 0, len = cs.length(); i < len; i++) {
                 if (!Character.isWhitespace(cs.charAt(i))) {
                     return false;
                 }
             }
        }
        return true;
	}
	
	private Strings() {
		throw new UnsupportedOperationException("Utility class");
	}
}
