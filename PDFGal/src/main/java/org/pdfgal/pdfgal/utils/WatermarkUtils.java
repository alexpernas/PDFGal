package org.pdfgal.pdfgal.utils;

import org.apache.pdfbox.pdmodel.PDPage;

public interface WatermarkUtils {

	/**
	 * Sets up the graphic state for a page, with the alpha contrast indicated.
	 * 
	 * @param page
	 * @param alpha
	 */
	public void setUpGraphicState(PDPage page, Float alpha);

}
