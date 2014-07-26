package org.pdfgal.pdfgal.pdfgal.impl;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.apache.pdfbox.pdmodel.common.PDPageLabels;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;
import org.pdfgal.pdfgal.exceptions.WatermarkOutOfLengthException;
import org.pdfgal.pdfgal.model.PDFGalBookmark;
import org.pdfgal.pdfgal.model.PDFGalPageNumbering;
import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.ConverterUtils;
import org.pdfgal.pdfgal.utils.WatermarkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFGalImpl implements PDFGal {

	@Autowired
	private ConverterUtils converterUtils;

	@Autowired
	private WatermarkUtils watermarkUtils;

	@Override
	public void merge(final List<String> inputUris, final String outputUri)
			throws COSVisitorException, IOException {

		if (CollectionUtils.isNotEmpty(inputUris)
				&& StringUtils.isNotBlank(outputUri)) {

			final PDFMergerUtility merger = new PDFMergerUtility();

			for (final String input : inputUris) {
				merger.addSource(input);
			}

			merger.setDestinationFileName(outputUri);
			merger.mergeDocuments();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public List<String> split(final String inputUri, final String outputUri,
			final List<Integer> pages) throws IOException, COSVisitorException {

		final List<String> result = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& CollectionUtils.isNotEmpty(pages)) {

			final PDDocument doc = PDDocument.load(inputUri);
			final List<PDDocument> splittedDocs = new ArrayList<PDDocument>();
			@SuppressWarnings("unchecked")
			final List<PDPage> pagesList = doc.getDocumentCatalog()
					.getAllPages();

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
				final String extension = this.converterUtils
						.addSubIndexBeforeExtension(outputUri, subIndex++);
				document.save(extension);
				result.add(extension);
			}

			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public List<String> split(final String inputUri, final String outputUri,
			final Integer pages) throws IOException, COSVisitorException {

		final List<String> result = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri) && pages != null) {

			final PDDocument doc = PDDocument.load(inputUri);

			final Splitter splitter = new Splitter();

			splitter.setSplitAtPage(pages);

			final List<PDDocument> splittedDocs = splitter.split(doc);

			Integer subIndex = 1;
			for (final PDDocument document : splittedDocs) {
				final String extension = this.converterUtils
						.addSubIndexBeforeExtension(outputUri, subIndex++);
				document.save(extension);
				result.add(extension);
				document.close();
			}

			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public void protect(final String inputUri, final String outputUri,
			final String password) throws IOException,
			BadSecurityHandlerException, COSVisitorException {

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(password)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final StandardProtectionPolicy pp = new StandardProtectionPolicy(
					password, password, new AccessPermission());
			doc.protect(pp);

			doc.save(outputUri);

			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void unProtect(final String inputUri, final String outputUri,
			final String password) throws IOException, COSVisitorException,
			BadSecurityHandlerException, CryptographyException {

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(password)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final DecryptionMaterial decryptionMaterial = new StandardDecryptionMaterial(
					password);
			doc.openProtection(decryptionMaterial);

			final StandardProtectionPolicy pp = new StandardProtectionPolicy(
					null, null, new AccessPermission());
			doc.protect(pp);

			doc.save(outputUri);

			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void putWatermark(final String inputUri, final String outputUri,
			final String text, final Color color, final Float alpha,
			final WatermarkPosition watermarkPosition, final List<Integer> pages)
			throws IOException, COSVisitorException,
			WatermarkOutOfLengthException {

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotBlank(text) && color != null
				&& alpha != null && watermarkPosition != null) {

			// If watermark position is not centered, then max length is the
			// same for landscape and portrait, text length is tested here so
			// there is no need to continue.
			if (!WatermarkPosition.CENTER.equals(watermarkPosition)
					&& text.length() > watermarkPosition.getMaxLengthPortrait()) {
				throw new WatermarkOutOfLengthException(
						Constants.WATERMARK_OUT_OF_LENGTH_EXCEPTION_MESSAGE);
			}

			final PDDocument doc = PDDocument.load(inputUri);
			final List<?> allPages = doc.getDocumentCatalog().getAllPages();

			this.converterUtils.deleteNonSelectedPositions(allPages, pages);

			if (CollectionUtils.isNotEmpty(allPages)) {
				for (final Object object : allPages) {
					final PDPage page = (PDPage) object;

					// The transparency, opacity of graphic objects can be set
					// directly
					// on the drawing commands but need to be set to a graphic
					// state
					// which will become part of the resources. Graphic state is
					// set
					// up.
					this.watermarkUtils.setUpGraphicState(page, alpha);

					// Now we will be able to call the state definition before
					// doing
					// the
					// drawing
					try {
						this.watermarkUtils.addWatermark(doc, page, color,
								text, watermarkPosition);
					} catch (final WatermarkOutOfLengthException e) {
						doc.close();
						throw e;
					}
				}
			}

			doc.save(outputUri);
			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void addBookmarks(final String inputUri, final String outputUri,
			final String title, final List<PDFGalBookmark> pdfGalBookmarksList)
			throws IOException, COSVisitorException {

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& StringUtils.isNotEmpty(title)
				&& CollectionUtils.isNotEmpty(pdfGalBookmarksList)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final PDDocumentOutline outline = new PDDocumentOutline();
			doc.getDocumentCatalog().setDocumentOutline(outline);
			final PDOutlineItem pagesOutline = new PDOutlineItem();
			pagesOutline.setTitle(title);
			@SuppressWarnings("unchecked")
			final List<PDPage> pages = doc.getDocumentCatalog().getAllPages();
			outline.appendChild(pagesOutline);

			for (final PDFGalBookmark pdfGalBookmark : pdfGalBookmarksList) {
				if (pdfGalBookmark != null && pdfGalBookmark.isInitializated()) {
					final PDPage page = pages.get(pdfGalBookmark.getPage() - 1);
					final PDPageFitWidthDestination dest = new PDPageFitWidthDestination();
					dest.setPage(page);
					final PDOutlineItem bookmark = new PDOutlineItem();
					bookmark.setDestination(dest);
					bookmark.setTitle(pdfGalBookmark.getText());
					pagesOutline.appendChild(bookmark);
				}
			}
			pagesOutline.openNode();
			outline.openNode();

			doc.save(outputUri);
			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public void reIndexPageNumbers(final String inputUri,
			final String outputUri,
			final List<PDFGalPageNumbering> pdfGalPageNumberingList)
			throws IOException, COSVisitorException {

		if (StringUtils.isNotBlank(inputUri)
				&& StringUtils.isNotBlank(outputUri)
				&& CollectionUtils.isNotEmpty(pdfGalPageNumberingList)) {

			final PDDocument doc = PDDocument.load(inputUri);

			final PDPageLabels pdPageLabels = new PDPageLabels(doc);

			for (final PDFGalPageNumbering pageNumbering : pdfGalPageNumberingList) {
				if (pageNumbering.isInitializated()) {
					final PDPageLabelRange pdPageLabelRange = new PDPageLabelRange();
					pdPageLabelRange.setStyle(pageNumbering.getNumberingStyle()
							.getValue());
					pdPageLabels
							.setLabelItem(pageNumbering.getPageNumber() - 1,
									pdPageLabelRange);
				}
			}

			doc.getDocumentCatalog().setPageLabels(pdPageLabels);

			doc.save(outputUri);
			doc.close();

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}
}
