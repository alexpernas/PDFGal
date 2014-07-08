package org.pdfgal.pdfgal.exceptions;

public class WatermarkOutOfLengthException extends IllegalArgumentException {

	public WatermarkOutOfLengthException(
			final String watermarkOutOfLengthExceptionMessage) {
		super(watermarkOutOfLengthExceptionMessage);
	}

	private static final long serialVersionUID = 3274587070045615436L;

}
