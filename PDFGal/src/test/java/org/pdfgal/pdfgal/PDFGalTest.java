package org.pdfgal.pdfgal;

import static org.junit.Assert.assertFalse;

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
		
		String inputUri = "/src/test/resources/test/protect/IProtectTest.pdf";
//		String inputUri = "\\src\\test\\resources\\test\\protect\\IProtectTest.pdf";
		String outputUri = "/PDFGal/src/test/resources/test/protect/OProtectTest.pdf";
		String password = "coNtra$1nA1";
		
		try {
			pdfGal.protect(inputUri, outputUri, password);
		} catch (Exception e) {
			assertFalse(true);
		}
		//TODO
		assertFalse(true);
	}
	
	@Test
	public void unProtect(){
		//TODO
		assertFalse(true);
	}

}
