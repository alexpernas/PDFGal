package org.pdfgal.pdfgal.utils.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.ConverterUtils;
import org.springframework.stereotype.Component;

/**
 * This class contains some converting methods.
 * 
 * @author Alex Pernas
 * 
 */
@Component
public class ConverterUtilsImpl implements ConverterUtils {

	@Override
	public String addSubIndexBeforeExtension(final String uri, final Integer subIndex) {

		String result = null;

		if (StringUtils.isNotBlank(uri) && uri.endsWith(Constants.PDF_EXTENSION)
				&& subIndex != null) {

			final StringBuffer sb = new StringBuffer();
			final Integer lengthWithoutExtension = uri.length() - Constants.PDF_EXTENSION.length();
			sb.append(uri.substring(0, lengthWithoutExtension));
			sb.append("_");
			sb.append(subIndex);
			sb.append(uri.substring(lengthWithoutExtension));
			result = sb.toString();

		} else {
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}

		return result;
	}

	@Override
	public void deleteNonSelectedPositions(final List<?> objectsList,
			final List<Integer> positionsList) {

		if (CollectionUtils.isNotEmpty(objectsList) && CollectionUtils.isNotEmpty(positionsList)) {
			Integer position = 1;
			final Iterator<?> iterator = objectsList.iterator();
			while (iterator.hasNext()) {
				iterator.next();
				if (!positionsList.contains(position)) {
					iterator.remove();
				}
				position++;
			}
		}
	}
}
