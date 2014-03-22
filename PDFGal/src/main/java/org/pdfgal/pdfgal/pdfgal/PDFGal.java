package org.pdfgal.pdfgal.pdfgal;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;

public interface PDFGal {
	
	/**
	 * This method merges PDFs. Parameter inputUris must contain a list of
	 * Strings which indicates the URIs where PDFs are located, ordered as
	 * they must be merged. Parameter outputUri indicates the URI where
	 * resultant PDF must be stored.
	 * @param inputUris
	 * @param outputUri
	 */
	public void merge(List<String> inputUris, String outputUri);
	
	/**
	 * This method splits PDFs. Parameter inputUri must contain the URI where the
	 * input PDF is stored.Parameter outputUri indicates the URI where resultant PDF
	 * must be stored. Parameter pages represents the list of pages, each element
	 * of this list indicates the first page of each resultant PDF (the first
	 * page of the first PDF should not be indicated).
	 * @param inputUri
	 * @param outputUri
	 * @param pages
	 */
	public void split(String inputUri, String outputUri, List<Integer> pages);
	
	/**
	 * This method password protects a PDF document. Parameter inputUri must
	 * contain the URI where the input PDF is stored. Parameter outputUri
	 * indicates the URI where resultant PDF must be stored. Parameter
	 * password contains the new password of the PDF.
	 * @param inputUri
	 * @param outputUri
	 * @param password
	 * @throws IOException 
	 * @throws BadSecurityHandlerException 
	 */
	public void protect(String inputUri, String outputUri, String password) throws IOException, BadSecurityHandlerException;

}
