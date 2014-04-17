package org.pdfgal.pdfgal.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * This class contains some converting methods.
 * @author Alex Pernas
 *
 */
@Component
public class ConverterUtils {
	
	/**
	 * This method returns the received URI with the
	 * specified subIndex before URI's extension.
	 * @param uri
	 * @param subIndex
	 * @return
	 */
	public String addSubIndexBeforeExtension(String uri, Integer subIndex){
		
		String result = null;
		
		if(StringUtils.isNotBlank(uri) && uri.endsWith(Constants.PDF_EXTENSION) && subIndex != null){
			
			StringBuffer sb = new StringBuffer();
			Integer lengthWithoutExtension = uri.length() - Constants.PDF_EXTENSION.length();
			sb.append(uri.substring(0, lengthWithoutExtension));
			sb.append("_");
			sb.append(subIndex);
			sb.append(uri.substring(lengthWithoutExtension));
			result = sb.toString();
			
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
		
		return result;
	}

}
