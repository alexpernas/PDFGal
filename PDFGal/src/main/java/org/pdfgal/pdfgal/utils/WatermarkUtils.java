package org.pdfgal.pdfgal.utils;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public interface WatermarkUtils {

	/**
	 * Sets up the graphic state for a page, with the alpha contrast indicated.
	 * 
	 * @param page
	 * @param alpha
	 */
	public void setUpGraphicState(PDPage page, Float alpha);

	/**
	 * Adds a watermark to the document's page, with the color indicated.
	 * 
	 * @param doc
	 * @param page
	 * @param color
	 * @param text
	 * @throws IOException
	 */
	public void addWatermark(PDDocument doc, PDPage page, Color color,
			String text) throws IOException;

}
