/*
 * PDFGal
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package org.pdfgal.pdfgal.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class for Unit Testing PDFGal validators.
 * 
 * @author Alex
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/common-test.xml" })
public class PDFGalValidatorTest {

	@Autowired
	PDFGalValidator pdfGalValidator;

	public static final String TEST_RESOURCES = "\\src\\test\\resources\\test\\validator\\";

	@Test
	public void isPDF() {

		final String inputUriTrue = System.getProperty("user.dir") + TEST_RESOURCES
				+ "ispdf\\IsPdfTestTrue.pdf";
		final String inputUriFalse = System.getProperty("user.dir") + TEST_RESOURCES
				+ "ispdf\\IsPdfTestFalse.txt";

		assertEquals(true, this.pdfGalValidator.isPDF(inputUriTrue));
		assertEquals(false, this.pdfGalValidator.isPDF(inputUriFalse));
	}

	@Test
	public void isEncrypted() {

		final String inputUriTrue = System.getProperty("user.dir") + TEST_RESOURCES
				+ "isencrypted\\IsEncryptedTestTrue.pdf";
		final String inputUriFalse = System.getProperty("user.dir") + TEST_RESOURCES
				+ "isencrypted\\IsEncryptedTestFalse.txt";

		assertEquals(true, this.pdfGalValidator.isEncrypted(inputUriTrue));
		assertEquals(false, this.pdfGalValidator.isEncrypted(inputUriFalse));
	}
}
