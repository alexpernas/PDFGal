package org.pdfgal.pdfgal.utils;

import java.io.IOException;

/**
 * This interface publishes various utils methods for extern applications.
 * 
 * @author Alex
 * 
 */
public interface PDFUtils {

	/**
	 * This method returns the number of pages of the PDF document stored into
	 * the argument's URI, or throws an {@link IOException} in case document
	 * can't be loaded or it is no PDF.
	 * 
	 * @param uri
	 * @return
	 * @throws IOException
	 */
	public Integer getPages(String uri) throws IOException;

}
