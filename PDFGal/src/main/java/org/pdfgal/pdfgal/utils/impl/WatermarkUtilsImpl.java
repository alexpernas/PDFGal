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
import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
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
			final String text, final WatermarkPosition watermarkPosition) throws IOException {

		if (doc != null && page != null && color != null && StringUtils.isNotBlank(text)
				&& watermarkPosition != null) {

			final PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
			contentStream.appendRawCommands("/TransparentState gs\n");
			contentStream.setNonStrokingColor(color);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 70);
			contentStream.setTextRotation(watermarkPosition.getRotationAngle(),
					watermarkPosition.getRotationTX(), watermarkPosition.getRotationTY());
			// TODO Facer que o texto estea centrado
			contentStream.drawString(text);
			contentStream.endText();
			contentStream.close();
		}
	}
}
