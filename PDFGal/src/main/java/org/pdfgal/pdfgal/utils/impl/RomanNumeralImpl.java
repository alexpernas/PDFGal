package org.pdfgal.pdfgal.utils.impl;

import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.RomanNumeral;

public class RomanNumeralImpl implements RomanNumeral {

	private static final Integer[] numbers = { 1000, 900, 500, 400, 100, 90,
			50, 40, 10, 9, 5, 4, 1 };

	private static final String[] letters = { "m", "cm", "d", "cd", "c", "xc",
			"l", "xl", "x", "ix", "v", "iv", "i" };

	@Override
	public String convertToRoman(Integer value) {

		String result = null;

		if (value != null) {
			for (Integer i = 0; i < numbers.length; i++) {
				while (value >= numbers[i]) {
					result += letters[i];
					value -= numbers[i];
				}
			}
		}

		if (result == null) {
			throw new IllegalArgumentException(Constants.NON_PARSEABLE_TO_ROMAN);
		}

		return result;
	}

}
