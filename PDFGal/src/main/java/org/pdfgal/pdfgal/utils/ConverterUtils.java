package org.pdfgal.pdfgal.utils;

import java.util.List;

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

	/**
	 * Deletes the elements in objectsList on the positions indicated on
	 * positionsList. Positions in positionsList are the natural position, so
	 * the element on position 0 in the objectsList is represented by the
	 * {@link Integer} 1 in the positionsList.
	 * 
	 * @param objectsList
	 * @param positionsList
	 */
	public void deleteNonSelectedPositions(List<?> objectsList,
			List<Integer> positionsList);

}
