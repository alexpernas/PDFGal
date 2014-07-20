package org.pdfgal.pdfgal.utils;

/**
 * This interface converts integer to roman numeral. Roman are lower case for
 * this app.
 * 
 * @author Alex
 * 
 */
public interface RomanNumeral {

	/**
	 * Converts an {@link Integer} number to roman.
	 * 
	 * @param value
	 * @return
	 */
	String convertToRoman(Integer value);

}
