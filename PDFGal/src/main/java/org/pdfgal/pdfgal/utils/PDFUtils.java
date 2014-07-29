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

import java.io.IOException;

/**
 * This interface publishes various utils methods for extern applications.
 * 
 * @author Alex
 * 
 */
public interface PDFUtils {

	/**
	 * This method returns the number of pages of the PDF document stored into
	 * the argument's URI, or throws an {@link IOException} in case document
	 * can't be loaded or it is no PDF.
	 * 
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public Integer getPages(String uri) throws IOException;

}
