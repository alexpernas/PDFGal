package org.pdfgal.pdfgal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class for Unit Testing PDFGal library.
 * @author Alex
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/common-test.xml"})
public class PDFGalTest {
	
	@Autowired
	PDFGal pdfGal;
	
	public static final String TEST_RESOURCES = "\\src\\test\\resources\\test\\";
	
	@Test
	public void merge(){
		//TODO
		assertFalse(true);
	}
	
	@Test
	public void split(){
		//TODO
		assertFalse(true);
	}
	
	@Test
	public void protect(){
		
		String inputUri = System.getProperty("user.dir") + TEST_RESOURCES + "protect\\IProtectTest.pdf";
		String outputUri = System.getProperty("user.dir") + TEST_RESOURCES + "protect\\OProtectTest.pdf";
		String password = "coNtra$1nA1";
		
		try {
			//Document is going to be protected
			pdfGal.protect(inputUri, outputUri, password);
		
			PDDocument doc = PDDocument.load(outputUri);
			
			try {
				//Try to open document with wrong password
				doc.openProtection(new StandardDecryptionMaterial(""));
			} catch (Exception e) {
				//With wrong password, exception must be thrown, so this is right
				assertTrue(true);
			}
			
			try{
				//Try to open document with right password, document should be opened
				doc.openProtection(new StandardDecryptionMaterial(password));
				assertTrue(true);
			} catch (Exception e){
				assertFalse(true);
			}
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}
	
	@Test
	public void unProtect() {
		
		String inputUri = System.getProperty("user.dir") + TEST_RESOURCES + "unprotect\\IUnProtectTest.pdf";
		String outputUri = System.getProperty("user.dir") + TEST_RESOURCES + "unprotect\\OUnProtectTest.pdf";
		String password = "coNtra$1nA1";
		
		try {
			//Document is going to be unprotected
			pdfGal.unProtect(inputUri, outputUri, password);
		
			PDDocument doc = PDDocument.load(outputUri);
			
			try {
				//Try to open document with previous password
				doc.openProtection(new StandardDecryptionMaterial(password));
			} catch (Exception e) {
				//With previous password, exception must be thrown, so this is right
				assertTrue(true);
			}
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}

}
