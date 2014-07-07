package org.pdfgal.pdfgal.utils.impl;

import java.util.Map;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDExtendedGraphicsState;
import org.pdfgal.pdfgal.utils.WatermarkUtils;
import org.springframework.stereotype.Component;

@Component
public class WatermarkUtilsImpl implements WatermarkUtils {

	@Override
	public void setUpGraphicState(final PDPage page, final Float alpha) {

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
