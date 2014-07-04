package org.pdfgal.pdfgal.utils.impl;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.pdfgal.pdfgal.utils.PDFUtils;
import org.springframework.stereotype.Component;

@Component
public class PDFUtilsImpl implements PDFUtils {

	@Override
	public Integer getPages(final String uri) throws IOException {
		final PDDocument document = PDDocument.load(uri);
		return document.getNumberOfPages();
	}

}
