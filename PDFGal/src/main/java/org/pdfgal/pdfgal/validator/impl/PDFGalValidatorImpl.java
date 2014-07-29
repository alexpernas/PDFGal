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

package org.pdfgal.pdfgal.validator.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
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

	@Override
	public boolean allLandscape(final String path) {

		boolean result = true;

		PDDocument document;
		try {
			document = PDDocument.load(path);

			@SuppressWarnings("unchecked")
			final List<PDPage> pagesList = document.getDocumentCatalog().getAllPages();
			if (CollectionUtils.isNotEmpty(pagesList)) {
				for (final PDPage page : pagesList) {
					if ((page != null) && (page.getMediaBox() != null)
							&& (page.getMediaBox().getHeight() > page.getMediaBox().getWidth())) {

						result = false;
						break;
					}
				}
			}

			document.close();
		} catch (final IOException e) {
			result = false;
		}

		return result;
	}
}
