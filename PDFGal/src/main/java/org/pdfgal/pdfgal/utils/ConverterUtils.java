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

package org.pdfgal.pdfgal.utils;

import java.util.List;

/**
 * This class contains some converting methods.
 * 
 * @author Alex Pernas
 * 
 */
public interface ConverterUtils {

	/**
	 * This method returns the received URI with the specified subIndex before
	 * URI's extension.
	 * 
	 * @param uri
	 * @param subIndex
	 * @return
	 */
	public String addSubIndexBeforeExtension(String uri, Integer subIndex);

	/**
	 * Deletes the elements in objectsList on the positions indicated on
	 * positionsList. Positions in positionsList are the natural position, so
	 * the element on position 0 in the objectsList is represented by the
	 * {@link Integer} 1 in the positionsList.
	 * 
	 * @param objectsList
	 * @param positionsList
	 */
	public void deleteNonSelectedPositions(List<?> objectsList, List<Integer> positionsList);

}
