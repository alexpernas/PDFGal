package org.pdfgal.pdfgal.utils;

/**
 * This class contains a few of library's constants.
 * 
 * @author Alex Pernas
 * 
 */
public class Constants {

	/**
	 * Default message for IllegalArgumentException.
	 */
	public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Method arguments must contain non null and non empty values.";

	/**
	 * Default message for an IllegalArgumentException caused by an invalid
	 * roman number.
	 */
	public static final String INVALID_ROMAN_NUMBER = "Argument is not a valid roman number.";

	/**
	 * Default message for an IllegalArgumentException caused by non parseable
	 * {@link Integer} to roman.
	 */
	public static final String NON_PARSEABLE_TO_ROMAN = "Integer is not parseable to roman number.";

	/**
	 * Default message for WatermarkOutOfLenghtException
	 */
	public static final String WATERMARK_OUT_OF_LENGTH_EXCEPTION_MESSAGE = "Watermark text is too long.";

	/**
	 * Extension of PDF documents.
	 */
	public static final String PDF_EXTENSION = ".pdf";

}
