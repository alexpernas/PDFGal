package org.pdfgal.pdfgal.pdfgal;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;

/**
 * This interface makes the operations with PDF files.
 * 
 * @author Alex
 * 
 */
public interface PDFGal {

	/**
	 * This method merges PDFs. Parameter inputUris must contain a list of
	 * Strings which indicates the URIs where PDFs are located, ordered as they
	 * must be merged. Parameter outputUri indicates the URI where resultant PDF
	 * must be stored.
	 * 
	 * @param inputUris
	 * @param outputUri
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public void merge(List<String> inputUris, String outputUri) throws COSVisitorException,
			IOException;

	/**
	 * This method splits PDFs. Parameter inputUri must contain the URI where
	 * the input PDF is stored. Parameter outputUri indicates the URI where
	 * resultant PDF must be stored. Parameter pages represents the list of
	 * pages, each element of this list indicates the first page of each
	 * resultant PDF (the first page of the first PDF should not be indicated).
	 * 
	 * @param inputUri
	 * @param outputUri
	 * @param pages
	 * @return The list of URIs where files are saved.
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public List<String> split(String inputUri, String outputUri, List<Integer> pages)
			throws IOException, COSVisitorException;

	/**
	 * This method splits PDFs. Parameter inputUri must contain the URI where
	 * the input PDF is stored. Parameter outputUri indicates the URI where
	 * resultant PDF must be stored. Parameter pages represents the number of
	 * pages each resultant document contains (last document may contain less
	 * than indicated pages).
	 * 
	 * @param inputUri
	 * @param outputUri
	 * @param pages
	 * @return The list of URIs where files are saved.
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public List<String> split(String inputUri, String outputUri, Integer pages) throws IOException,
			COSVisitorException;

	/**
	 * This method password protects a PDF document. Parameter inputUri must
	 * contain the URI where the input PDF is stored. Parameter outputUri
	 * indicates the URI where resultant PDF must be stored. Parameter password
	 * contains the new password of the PDF.
	 * 
	 * @param inputUri
	 * @param outputUri
	 * @param password
	 * @throws IOException
	 * @throws BadSecurityHandlerException
	 * @throws COSVisitorException
	 */
	public void protect(String inputUri, String outputUri, String password) throws IOException,
			BadSecurityHandlerException, COSVisitorException;

	/**
	 * This method eliminates password protection into a PDF document. Parameter
	 * inputUri must contain the URI where the input PDF is stored. Parameter
	 * outputUri indicates the URI where resultant PDF must be stored. Parameter
	 * password contains the current password of the PDF.
	 * 
	 * @param inputUri
	 * @param outputUri
	 * @param password
	 * @throws IOException
	 * @throws COSVisitorException
	 * @throws CryptographyException
	 * @throws BadSecurityHandlerException
	 */
	public void unProtect(String inputUri, String outputUri, String password) throws IOException,
			COSVisitorException, BadSecurityHandlerException, CryptographyException;

	/**
	 * Puts a watermark in the document with the indicated text.
	 * 
	 * @param inputUri
	 * @param outputUri
	 * @param text Text for the watermark
	 * @param color Color of the watermark
	 * @throws IOException
	 * @throws COSVisitorException
	 */
	public void putWatermark(String inputUri, String outputUri, String text, Color color)
			throws IOException, COSVisitorException;

}
