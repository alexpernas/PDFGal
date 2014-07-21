package org.pdfgal.pdfgal.model;

import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;

public interface PDFGalPageNumbering {

	Integer getPageNumber();

	void setPageNumber(Integer pageNumber);

	NumberingStyle getNumberingStyle();

	void setNumberingStyle(NumberingStyle numberingStyle);

	boolean isInitializated();

}
