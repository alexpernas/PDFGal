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

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.pdfgal.pdfgal.exceptions.WatermarkOutOfLengthException;
import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;

public interface WatermarkUtils {

	/**
	 * Sets up the graphic state for a page, with the alpha contrast indicated.
	 * 
	 * @param page
	 * @param alpha
	 */
	public void setUpGraphicState(PDPage page, Float alpha);

	/**
	 * Adds a watermark to the document's page, with the color and position
	 * indicated.
	 * 
	 * @param doc
	 * @param page
	 * @param color
	 * @param text
	 * @param watermarkPosition
	 * @throws IOException
	 * @throws WatermarkOutOfLengthException
	 */
	public void addWatermark(PDDocument doc, PDPage page, Color color, String text,
			WatermarkPosition watermarkPosition) throws IOException, WatermarkOutOfLengthException;

}
