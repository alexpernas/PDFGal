package org.pdfgal.pdfgal.validator;

/**
 * This class makes some PDF validations.
 * 
 * @author Alex
 * 
 */
public interface PDFGalValidator {

	/**
	 * This method returns true when the file in the path is a PDF, it returns
	 * false when it is not a PDF or it does not exist.
	 * 
	 * @param path
	 * @return
	 */
	public boolean isPDF(String path);

	/**
	 * This method returns true when the file in the path is an encrypted PDF
	 * file, otherwise it returns false.
	 * 
	 * @param path
	 * @return
	 */
	public boolean isEncrypted(String path);

}
