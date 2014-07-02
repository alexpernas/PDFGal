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

		final String inputUriTrue = System.getProperty("user.dir")
				+ TEST_RESOURCES + "ispdf\\IsPdfTestTrue.pdf";
		final String inputUriFalse = System.getProperty("user.dir")
				+ TEST_RESOURCES + "ispdf\\IsPdfTestFalse.txt";

		assertEquals(true, this.pdfGalValidator.isPDF(inputUriTrue));
		assertEquals(false, this.pdfGalValidator.isPDF(inputUriFalse));
	}

	@Test
	public void isEncrypted() {

		final String inputUriTrue = System.getProperty("user.dir")
				+ TEST_RESOURCES + "isencrypted\\IsEncryptedTestTrue.pdf";
		final String inputUriFalse = System.getProperty("user.dir")
				+ TEST_RESOURCES + "isencrypted\\IsEncryptedTestFalse.txt";

		assertEquals(true, this.pdfGalValidator.isEncrypted(inputUriTrue));
		assertEquals(false, this.pdfGalValidator.isEncrypted(inputUriFalse));
	}
}
