package org.pdfgal.pdfgal.model.vo;

import org.pdfgal.pdfgal.model.PDFGalPageNumbering;
import org.pdfgal.pdfgal.model.enumerated.NumberingStyle;

public class PDFGalPageNumberingVO implements PDFGalPageNumbering {

	private Integer pageNumber;

	private NumberingStyle numberingStyle;

	public PDFGalPageNumberingVO() {
		super();
	}

	/**
	 * @param pageNumber
	 * @param numberingStyle
	 */
	public PDFGalPageNumberingVO(final Integer pageNumber, final NumberingStyle numberingStyle) {
		super();
		this.pageNumber = pageNumber;
		this.numberingStyle = numberingStyle;
	}

	/**
	 * @return the pageNumber
	 */
	@Override
	public Integer getPageNumber() {
		return this.pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	@Override
	public void setPageNumber(final Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the numberingStyle
	 */
	@Override
	public NumberingStyle getNumberingStyle() {
		return this.numberingStyle;
	}

	/**
	 * @param numberingStyle the numberingStyle to set
	 */
	@Override
	public void setNumberingStyle(final NumberingStyle numberingStyle) {
		this.numberingStyle = numberingStyle;
	}

	@Override
	public boolean isInitializated() {
		return (this.pageNumber != null) && (this.numberingStyle != null);
	}
}
