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

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.model.PDFGalBookmark;

public class PDFGalBookmarkVO implements PDFGalBookmark {

	private Integer page;

	private String text;

	public PDFGalBookmarkVO() {
		super();
	}

	public PDFGalBookmarkVO(final Integer page, final String text) {
		super();
		this.page = page;
		this.text = text;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public void setPage(final Integer page) {
		this.page = page;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public boolean isInitializated() {
		return (this.page != null) && StringUtils.isNotEmpty(this.text);
	}

}
