package org.pdfgal.pdfgal.pdfgal.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
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
			}

			// This section splits the last document
			final PDDocument lastDocument = new PDDocument();
			for (Integer i = currentPage; i < pagesList.size(); i++) {
				lastDocument.addPage(pagesList.get(i));
			}
			splittedDocs.add(lastDocument);

			Integer subIndex = 1;
			for (final PDDocument document : splittedDocs) {
				final String extension = this.converterUtils
						.addSubIndexBeforeExtension(outputUri, subIndex++);
				document.save(extension);
				result.add(extension);
			}

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
			}

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

		} else {
			throw new IllegalArgumentException(
					Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

}
