package org.pdfgal.pdfgal.utils;

/**
 * This class contains some converting methods.
 * 
 * @author Alex Pernas
 * 
 */
public interface ConverterUtils {

	/**
	 * This method returns the received URI with the specified subIndex before
	 * URI's extension.
	 * 
	 * @param uri
	 * @param subIndex
	 * @return
	 */
	public String addSubIndexBeforeExtension(String uri, Integer subIndex);

}
