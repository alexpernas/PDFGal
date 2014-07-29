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
