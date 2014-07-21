package org.pdfgal.pdfgal.model.enumerated;

public enum NumberingStyle {

	STYLE_DECIMAL("D"), STYLE_ROMAN_UPPER("R"), STYLE_ROMAN_LOWER("r"), STYLE_LETTERS_UPPER("A"), STYLE_LETTERS_LOWER(
			"a");

	private String value;

	/**
	 * @param value
	 */
	private NumberingStyle(final String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(final String value) {
		this.value = value;
	}

}
