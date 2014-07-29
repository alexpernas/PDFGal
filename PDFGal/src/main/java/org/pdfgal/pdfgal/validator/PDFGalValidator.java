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

package org.pdfgal.pdfgal.validator;

/**
 * This class makes some PDF validations.
 * 
 * @author Alex
 * 
 */
public interface PDFGalValidator {

	/**
	 * This method returns true when the file in the path is a PDF, it returns
	 * false when it is not a PDF or it does not exist.
	 * 
	 * @param path
	 * @return
	 */
	public boolean isPDF(String path);

	/**
	 * This method returns true when the file in the path is an encrypted PDF
	 * file, otherwise it returns false.
	 * 
	 * @param path
	 * @return
	 */
	public boolean isEncrypted(String path);

	/**
	 * This method returns true when every page in the document is landscape,
	 * otherwise it returns false;
	 * 
	 * @return
	 */
	public boolean allLandscape(String path);

}
