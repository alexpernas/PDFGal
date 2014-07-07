package org.pdfgal.pdfgal.pdfgal.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.PDExtendedGraphicsState;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFGalImpl implements PDFGal {

	@Autowired
	ConverterUtils converterUtils;

	@Override
	public void merge(final List<String> inputUris, final String outputUri)
			throws COSVisitorException, IOException {

		if (CollectionUtils.isNotEmpty(inputUris) && StringUtils.isNotBlank(outputUri)) {

			final PDFMergerUtility merger = new PDFMergerUtility();

			for (final String input : inputUris) {
				merger.addSource(input);
			}

			merger.setDestinationFileName(outputUri);
			merger.mergeDocuments();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public List<String> split(final String inputUri, final String outputUri,
			final List<Integer> pages) throws IOException, COSVisitorException {

		final List<String> result = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri)
				&& CollectionUtils.isNotEmpty(pages)) {

			final PDDocument doc = PDDocument.load(inputUri);
			final List<PDDocument> splittedDocs = new ArrayList<PDDocument>();
			@SuppressWarnings("unchecked")
			final List<PDPage> pagesList = doc.getDocumentCatalog().getAllPages();

			// This section creates a new document for each split
			// indicated into the list, except the last one.
			Integer currentPage = 0;
			for (final Integer page : pages) {
				final PDDocument document = new PDDocument();
				for (Integer i = currentPage; i <= page - 2; i++) {
					document.addPage(pagesList.get(i));
				}
				splittedDocs.add(document);
				currentPage = page - 1;
				document.close();
			}

			// This section splits the last document
			final PDDocument lastDocument = new PDDocument();
			for (Integer i = currentPage; i < pagesList.size(); i++) {
				lastDocument.addPage(pagesList.get(i));
			}
			splittedDocs.add(lastDocument);
			lastDocument.close();

			Integer subIndex = 1;
			for (final PDDocument document : splittedDocs) {
				final String extension = this.converterUtils.addSubIndexBeforeExtension(outputUri,
						subIndex++);
				document.save(extension);
				result.add(extension);
			}

			doc.close();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public List<String> split(final String inputUri, final String outputUri, final Integer pages)
			throws IOException, COSVisitorException {

		final List<String> result = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) && pages != null) {

			final PDDocument doc = PDDocument.load(inputUri);

			final Splitter splitter = new Splitter();

			splitter.setSplitAtPage(pages);

			final List<PDDocument> splittedDocs = splitter.split(doc);

			Integer subIndex = 1;
			for (final PDDocument document : splittedDocs) {
				final String extension = this.converterUtils.addSubIndexBeforeExtension(outputUri,
						subIndex++);
				document.save(extension);
				result.add(extension);
				document.close();
			}

			doc.close();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public void protect(final String inputUri, final String outputUri, final String password)
			throws IOException, BadSecurityHandlerException, COSVisitorException {

		if (StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(password)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final StandardProtectionPolicy pp = new StandardProtectionPolicy(password, password,
					new AccessPermission());
			doc.protect(pp);

			doc.save(outputUri);

			doc.close();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void unProtect(final String inputUri, final String outputUri, final String password)
			throws IOException, COSVisitorException, BadSecurityHandlerException,
			CryptographyException {

		if (StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(password)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final DecryptionMaterial decryptionMaterial = new StandardDecryptionMaterial(password);
			doc.openProtection(decryptionMaterial);

			final StandardProtectionPolicy pp = new StandardProtectionPolicy(null, null,
					new AccessPermission());
			doc.protect(pp);

			doc.save(outputUri);

			doc.close();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void putWatermark(final String inputUri, final String outputUri, final String text,
			final Color color) throws IOException, COSVisitorException {
		// TODO Auto-generated method stub

		if (StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(text) && color != null) {

			final PDDocument doc = PDDocument.load(inputUri);
			final PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(0);

			// The transparency, opacity of graphic objects can be set directly
			// on the drawing commands but need to be set to a graphic state
			// which will become part of the resources.

			/* Set up the graphic state */

			// Define a new extended graphic state
			final PDExtendedGraphicsState extendedGraphicsState = new PDExtendedGraphicsState();
			// Set the transparency/opacity
			extendedGraphicsState.setNonStrokingAlphaConstant(0.5f);
			// Get the page resources.
			final PDResources resources = page.findResources();

			// Get the defined graphic states.
			final Map<String, PDExtendedGraphicsState> graphicsStateDictionary = resources
					.getGraphicsStates();

			graphicsStateDictionary.put("TransparentState", extendedGraphicsState);
			resources.setGraphicsStates(graphicsStateDictionary);

			/* End of setup */

			// Now we will be able to call the state definition before doing the
			// drawing
			final PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
			contentStream.appendRawCommands("/TransparentState gs\n");
			contentStream.setNonStrokingColor(color);
			contentStream.beginText();
			contentStream.setFont(PDType1Font.HELVETICA, 72);
			contentStream.moveTextPositionByAmount(10, 10);
			contentStream.setTextRotation(1, 100, 100);
			contentStream.drawString(text);
			contentStream.endText();
			contentStream.close();

			doc.save(outputUri);
			doc.close();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}
}
