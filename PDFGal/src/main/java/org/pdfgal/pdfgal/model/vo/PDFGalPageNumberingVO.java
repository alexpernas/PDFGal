/*
 * PDFGal
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

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
