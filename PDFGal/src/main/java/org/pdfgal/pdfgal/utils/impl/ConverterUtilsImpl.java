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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.ConverterUtils;
import org.springframework.stereotype.Component;

/**
 * This class contains some converting methods.
 * 
 * @author Alex Pernas
 * 
 */
@Component
public class ConverterUtilsImpl implements ConverterUtils {

	@Override
	public String addSubIndexBeforeExtension(final String uri, final Integer subIndex) {

		String result = null;

		if (StringUtils.isNotBlank(uri) && uri.endsWith(Constants.PDF_EXTENSION)
				&& subIndex != null) {

			final StringBuffer sb = new StringBuffer();
			final Integer lengthWithoutExtension = uri.length() - Constants.PDF_EXTENSION.length();
			sb.append(uri.substring(0, lengthWithoutExtension));
			sb.append("_");
			sb.append(subIndex);
			sb.append(uri.substring(lengthWithoutExtension));
			result = sb.toString();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public void deleteNonSelectedPositions(final List<?> objectsList,
			final List<Integer> positionsList) {

		if (CollectionUtils.isNotEmpty(objectsList) && CollectionUtils.isNotEmpty(positionsList)) {
			Integer position = 1;
			final Iterator<?> iterator = objectsList.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				if (!positionsList.contains(position)) {
					iterator.remove();
				}
				position++;
			}
		}
	}
}
