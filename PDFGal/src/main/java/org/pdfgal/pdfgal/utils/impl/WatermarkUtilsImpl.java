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

import java.awt.Color;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.PDExtendedGraphicsState;
import org.pdfgal.pdfgal.exceptions.WatermarkOutOfLengthException;
import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.WatermarkUtils;
import org.springframework.stereotype.Component;

@Component
public class WatermarkUtilsImpl implements WatermarkUtils {

	@Override
	public void setUpGraphicState(final PDPage page, final Float alpha) {

		if (page != null && alpha != null) {

			// Define a new extended graphic state
			final PDExtendedGraphicsState extendedGraphicsState = new PDExtendedGraphicsState();
			// Set the transparency/opacity
			extendedGraphicsState.setNonStrokingAlphaConstant(alpha);
			// Get the page resources.
			final PDResources resources = page.findResources();

			// Get the defined graphic states.
			final Map<String, PDExtendedGraphicsState> graphicsStateDictionary = resources
					.getGraphicsStates();

			graphicsStateDictionary.put("TransparentState", extendedGraphicsState);
			resources.setGraphicsStates(graphicsStateDictionary);
		}
	}

	@Override
	public void addWatermark(final PDDocument doc, final PDPage page, final Color color,
			final String text, final WatermarkPosition watermarkPosition) throws IOException,
			WatermarkOutOfLengthException {

		if (doc != null && page != null && color != null && StringUtils.isNotBlank(text)
				&& watermarkPosition != null) {

			// Attributes are extrated from the watermarkPosition argument.
			Double rotationAngle = 0D;
			Double rotationTX = 0D;
			Double rotationTY = 0D;
			Integer maxLength = 0;

			if (page.getMediaBox().getHeight() > page.getMediaBox().getWidth()) {
				// Page is portrait
				rotationAngle = watermarkPosition.getRotationAnglePortrait();
				rotationTX = watermarkPosition.getRotationTXPortrait();
				rotationTY = watermarkPosition.getRotationTYPortrait();
				maxLength = watermarkPosition.getMaxLengthPortrait();
			} else {
				// Page is landscape
				rotationAngle = watermarkPosition.getRotationAngleLandscape();
				rotationTX = watermarkPosition.getRotationTXLandscape();
				rotationTY = watermarkPosition.getRotationTYLandscape();
				maxLength = watermarkPosition.getMaxLengthLandscape();
			}

			// In case text is too large, an exception is thrown.
			if (text.length() > maxLength) {
				throw new WatermarkOutOfLengthException(
						Constants.WATERMARK_OUT_OF_LENGTH_EXCEPTION_MESSAGE);
			}

			final PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
			contentStream.appendRawCommands("/TransparentState gs\n");
			contentStream.setNonStrokingColor(color);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 70);
			contentStream.setTextRotation(rotationAngle, rotationTX, rotationTY);
			// Text is centered
			final Integer size = (maxLength * 2) - text.length();
			final String centeredText = StringUtils.center(text, size);
			contentStream.drawString(centeredText);
			contentStream.endText();
			contentStream.close();
		}
	}
}
