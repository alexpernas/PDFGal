package org.pdfgal.pdfgal.utils.impl;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.pdfgal.pdfgal.utils.PDFUtils;
import org.springframework.stereotype.Component;

@Component
public class PDFUtilsImpl implements PDFUtils {

	@Override
	public Integer getPages(final String uri) throws IOException {
		if (StringUtils.isBlank(uri)) {
			throw new IOException();
		}
		final PDDocument document = PDDocument.load(uri);
		final Integer numberOfPages = document.getNumberOfPages();
		document.close();
		return numberOfPages;
	}

}
