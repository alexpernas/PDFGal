package org.pdfgal.pdfgal.validator.impl;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.pdfgal.pdfgal.validator.PDFGalValidator;
import org.springframework.stereotype.Component;

@Component
public class PDFGalValidatorImpl implements PDFGalValidator {

	@Override
	public boolean isPDF(final String path) {

		boolean result = false;

		try {
			final PDDocument document = PDDocument.load(path);
			document.close();
			result = true;
		} catch (final IOException e) {
			result = false;
		}

		return result;
	}

	@Override
	public boolean isEncrypted(final String path) {

		boolean result = false;

		PDDocument document;
		try {
			document = PDDocument.load(path);
			result = document.isEncrypted();
			document.close();
		} catch (final IOException e) {
			result = false;
		}

		return result;
	}
}
