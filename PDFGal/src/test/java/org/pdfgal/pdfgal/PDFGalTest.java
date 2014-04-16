package org.pdfgal.pdfgal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
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
	
	@SuppressWarnings("unchecked")
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
			
			List<PDPage> inputDoc1Pages = inputDoc1.getDocumentCatalog().getAllPages();
			List<PDPage> inputDoc2Pages = inputDoc2.getDocumentCatalog().getAllPages();
			List<PDPage> inputDoc3Pages = inputDoc3.getDocumentCatalog().getAllPages();
			List<PDPage> inputDoc4Pages = inputDoc4.getDocumentCatalog().getAllPages();
			List<PDPage> inputDoc5Pages = inputDoc5.getDocumentCatalog().getAllPages();
			List<PDPage> outputDocPages = outputDoc.getDocumentCatalog().getAllPages();
			
			Integer inputDocsPagesTotal = inputDoc1Pages.size() + inputDoc2Pages.size() +
					inputDoc3Pages.size() + inputDoc4Pages.size() + inputDoc5Pages.size(); 
			assertEquals(new Integer(outputDocPages.size()), inputDocsPagesTotal);
			
			for(PDPage outputPage : outputDocPages){
				
				PDStream outputPageContents = outputPage.getContents();
				PDStream inputPageContents = null;
				
				if(CollectionUtils.isNotEmpty(inputDoc1Pages)){
//					assertEquals(outputPage, inputDoc1Pages.get(0));
					inputPageContents = inputDoc1Pages.get(0).getContents();//TODO
					assertEquals(outputPageContents, inputPageContents);
					inputDoc1Pages.remove(0);
					
				}else if(CollectionUtils.isNotEmpty(inputDoc2Pages)){
					assertEquals(outputPage, inputDoc2Pages.get(0));//TODO
					inputDoc2Pages.remove(0);
					
				}else if(CollectionUtils.isNotEmpty(inputDoc3Pages)){
					assertEquals(outputPage, inputDoc3Pages.get(0));//TODO
					inputDoc3Pages.remove(0);
					
				}else if(CollectionUtils.isNotEmpty(inputDoc4Pages)){
					assertEquals(outputPage, inputDoc4Pages.get(0));//TODO
					inputDoc4Pages.remove(0);
					
				}else if(CollectionUtils.isNotEmpty(inputDoc5Pages)){
					assertEquals(outputPage, inputDoc5Pages.get(0));//TODO
					inputDoc5Pages.remove(0);
					
				}
			}
			
		}catch(Exception e){
			assertFalse(true);
		}
		
		//TODO
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
