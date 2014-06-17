package org.pdfgal.pdfgal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Class for Unit Testing PDFGal library.
 * @author Alex Pernas
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
		
		String inputUri1 = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\IMergeTest_1.pdf";
		String inputUri2 = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\IMergeTest_2.pdf";
		String inputUri3 = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\IMergeTest_3.pdf";
		String inputUri4 = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\IMergeTest_4.pdf";
		String inputUri5 = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\IMergeTest_5.pdf";
		String outputUri = System.getProperty("user.dir") + TEST_RESOURCES + "merge\\OMergeTest.pdf";
		
		List<String> inputUris = new ArrayList<String>();
		inputUris.add(inputUri1);
		inputUris.add(inputUri2);
		inputUris.add(inputUri3);
		inputUris.add(inputUri4);
		inputUris.add(inputUri5);
		
		try{
			pdfGal.merge(inputUris, outputUri);
			
			PDDocument inputDoc1 = PDDocument.load(inputUri1);
			PDDocument inputDoc2 = PDDocument.load(inputUri2);
			PDDocument inputDoc3 = PDDocument.load(inputUri3);
			PDDocument inputDoc4 = PDDocument.load(inputUri4);
			PDDocument inputDoc5 = PDDocument.load(inputUri5);
			PDDocument outputDoc = PDDocument.load(outputUri);
			
			Integer inputDoc1Size = inputDoc1.getNumberOfPages();
			Integer inputDoc2Size = inputDoc2.getNumberOfPages();
			Integer inputDoc3Size = inputDoc3.getNumberOfPages();
			Integer inputDoc4Size = inputDoc4.getNumberOfPages();
			Integer inputDoc5Size = inputDoc5.getNumberOfPages();
			
			Integer inputDocsPagesTotal = inputDoc1Size + inputDoc2Size + inputDoc3Size + inputDoc4Size + inputDoc5Size; 
			assertEquals(new Integer(outputDoc.getNumberOfPages()), inputDocsPagesTotal);
			
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String inputText = null;
			String outputText = null;
			Integer lastOutputPage = null;
			
			/* Let's compare the first document */
			//Extraction of output text
			lastOutputPage = inputDoc1Size;
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(lastOutputPage);
			outputText = pdfStripper.getText(outputDoc);
			//Extraction of input text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(inputDoc1Size);
			inputText = pdfStripper.getText(inputDoc1);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the second document */
			//Extraction of output text
			pdfStripper.setStartPage(lastOutputPage + 1);
			lastOutputPage = lastOutputPage + inputDoc2Size;
			pdfStripper.setEndPage(lastOutputPage);
			outputText = pdfStripper.getText(outputDoc);
			//Extraction of input text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(inputDoc2Size);
			inputText = pdfStripper.getText(inputDoc2);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the third document */
			//Extraction of output text
			pdfStripper.setStartPage(lastOutputPage + 1);
			lastOutputPage = lastOutputPage + inputDoc3Size;
			pdfStripper.setEndPage(lastOutputPage);
			outputText = pdfStripper.getText(outputDoc);
			//Extraction of input text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(inputDoc3Size);
			inputText = pdfStripper.getText(inputDoc3);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the fourth document */
			//Extraction of output text
			pdfStripper.setStartPage(lastOutputPage + 1);
			lastOutputPage = lastOutputPage + inputDoc4Size;
			pdfStripper.setEndPage(lastOutputPage);
			outputText = pdfStripper.getText(outputDoc);
			//Extraction of input text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(inputDoc4Size);
			inputText = pdfStripper.getText(inputDoc4);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the fifth document */
			//Extraction of output text
			pdfStripper.setStartPage(lastOutputPage + 1);
			lastOutputPage = lastOutputPage + inputDoc5Size;
			pdfStripper.setEndPage(lastOutputPage);
			outputText = pdfStripper.getText(outputDoc);
			//Extraction of input text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(inputDoc5Size);
			inputText = pdfStripper.getText(inputDoc5);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
		}catch(Exception e){
			assertFalse(true);
		}
	}

	
	/**
	 * Test for method PDFGal.split(String inputUri, String outputUri, List<Integer> pages) throws IOException, COSVisitorException;
	 */
	@Test
	public void splitSettingPageNumber(){
		
		String inputUri = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\ISplitListTest.pdf";
		String outputUri = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\OSplitListTest.pdf";
		String outputUri1 = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\OSplitListTest_1.pdf";
		String outputUri2 = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\OSplitListTest_2.pdf";
		String outputUri3 = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\OSplitListTest_3.pdf";
		String outputUri4 = System.getProperty("user.dir") + TEST_RESOURCES + "splitlist\\OSplitListTest_4.pdf";
		
		List<Integer> pages = new ArrayList<Integer>();
		pages.add(2);
		pages.add(5);
		pages.add(7);
		
		try {
			pdfGal.split(inputUri, outputUri, pages);
			
			PDDocument outputDoc1 = PDDocument.load(outputUri1);
			PDDocument outputDoc2 = PDDocument.load(outputUri2);
			PDDocument outputDoc3 = PDDocument.load(outputUri3);
			PDDocument outputDoc4 = PDDocument.load(outputUri4);
			PDDocument inputDoc = PDDocument.load(inputUri);
			
			Integer outputDoc1Size = outputDoc1.getNumberOfPages();
			Integer outputDoc2Size = outputDoc2.getNumberOfPages();
			Integer outputDoc3Size = outputDoc3.getNumberOfPages();
			Integer outputDoc4Size = outputDoc4.getNumberOfPages();
			
			Integer inputDocsPagesTotal = outputDoc1Size + outputDoc2Size + outputDoc3Size + outputDoc4Size; 
			assertEquals(new Integer(inputDoc.getNumberOfPages()), inputDocsPagesTotal);
			
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String inputText = null;
			String outputText = null;
			Integer lastInputPage = null;
			
			/* Let's compare the first document */
			//Extraction of input text
			lastInputPage = outputDoc1Size;
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc1Size);
			outputText = pdfStripper.getText(outputDoc1);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the second document */
			//Extraction of input text
			pdfStripper.setStartPage(lastInputPage + 1);
			lastInputPage = lastInputPage + outputDoc2Size;
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc2Size);
			outputText = pdfStripper.getText(outputDoc2);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the third document */
			//Extraction of input text
			pdfStripper.setStartPage(lastInputPage + 1);
			lastInputPage = lastInputPage + outputDoc3Size;
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc3Size);
			outputText = pdfStripper.getText(outputDoc3);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the fourth document */
			//Extraction of input text
			pdfStripper.setStartPage(lastInputPage + 1);
			lastInputPage = lastInputPage + outputDoc4Size;
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc4Size);
			outputText = pdfStripper.getText(outputDoc4);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
		} catch (Exception e){
			assertFalse(true);
		}
	}
	
	/**
	 * Test for method PDFGal.split(String inputUri, String outputUri, Integer pages) throws IOException, COSVisitorException;
	 */
	@Test
	public void splitSettingNumberOfPages(){
		
		String inputUri = System.getProperty("user.dir") + TEST_RESOURCES + "splitinteger\\ISplitIntegerTest.pdf";
		String outputUri = System.getProperty("user.dir") + TEST_RESOURCES + "splitinteger\\OSplitIntegerTest.pdf";
		String outputUri1 = System.getProperty("user.dir") + TEST_RESOURCES + "splitinteger\\OSplitIntegerTest_1.pdf";
		String outputUri2 = System.getProperty("user.dir") + TEST_RESOURCES + "splitinteger\\OSplitIntegerTest_2.pdf";
		String outputUri3 = System.getProperty("user.dir") + TEST_RESOURCES + "splitinteger\\OSplitIntegerTest_3.pdf";
		
		Integer pages = 3;
		
		try {
			pdfGal.split(inputUri, outputUri, pages);
			
			PDDocument outputDoc1 = PDDocument.load(outputUri1);
			PDDocument outputDoc2 = PDDocument.load(outputUri2);
			PDDocument outputDoc3 = PDDocument.load(outputUri3);
			PDDocument inputDoc = PDDocument.load(inputUri);
			
			Integer outputDoc1Size = outputDoc1.getNumberOfPages();
			Integer outputDoc2Size = outputDoc2.getNumberOfPages();
			Integer outputDoc3Size = outputDoc3.getNumberOfPages();
			
			Integer inputDocsPagesTotal = outputDoc1Size + outputDoc2Size + outputDoc3Size; 
			assertEquals(new Integer(inputDoc.getNumberOfPages()), inputDocsPagesTotal);
			
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String inputText = null;
			String outputText = null;
			Integer lastInputPage = null;
			
			/* Let's compare the first document */
			//Extraction of input text
			lastInputPage = outputDoc1Size;
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc1Size);
			outputText = pdfStripper.getText(outputDoc1);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the second document */
			//Extraction of input text
			pdfStripper.setStartPage(lastInputPage + 1);
			lastInputPage = lastInputPage + outputDoc2Size;
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc2Size);
			outputText = pdfStripper.getText(outputDoc2);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
			/* Let's compare the third document */
			//Extraction of input text
			pdfStripper.setStartPage(lastInputPage + 1);
			lastInputPage = lastInputPage + outputDoc3Size;
			pdfStripper.setEndPage(lastInputPage);
			inputText = pdfStripper.getText(inputDoc);
			//Extraction of output text
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(outputDoc3Size);
			outputText = pdfStripper.getText(outputDoc3);
			//Comparison of output and input text
			assertEquals(outputText, inputText);
			
		} catch (Exception e){
			assertFalse(true);
		}
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
