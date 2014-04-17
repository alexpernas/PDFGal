package org.pdfgal.pdfgal.pdfgal.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgal.utils.Constants;
import org.pdfgal.pdfgal.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFGalImpl implements PDFGal {
	
	@Autowired
	ConverterUtils converterUtils;

	public void merge(List<String> inputUris, String outputUri) throws COSVisitorException, IOException {
		
		if(CollectionUtils.isNotEmpty(inputUris) && StringUtils.isNotBlank(outputUri)){
			
			PDFMergerUtility merger = new PDFMergerUtility();
		
			for(String input : inputUris){
				merger.addSource(input);
			}
			
			merger.setDestinationFileName(outputUri);
			merger.mergeDocuments();
			
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	public void split(String inputUri, String outputUri, List<Integer> pages) throws IOException, COSVisitorException {
		
		if(StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) &&
				CollectionUtils.isNotEmpty(pages)){
			
			PDDocument doc = PDDocument.load(inputUri);
			List<PDDocument> splittedDocs = new ArrayList<PDDocument>();
			@SuppressWarnings("unchecked")
			List<PDPage> pagesList = (List<PDPage>) doc.getDocumentCatalog().getAllPages();
			
			//This section creates a new document for each split
			//indicated into the list, except the last one.
			Integer currentPage = 0;
			for(Integer page : pages){
				PDDocument document = new PDDocument();
				for(Integer i = currentPage; i <= page - 2; i++){
					document.addPage(pagesList.get(i));
				}
				splittedDocs.add(document);
				currentPage = page - 1;
			}
			
			//This section splits the last document
			PDDocument lastDocument = new PDDocument();
			for(Integer i = currentPage; i < pagesList.size(); i++){
				lastDocument.addPage(pagesList.get(i));
			}
			splittedDocs.add(lastDocument);
			
			Integer subIndex = 1;
			for(PDDocument document : splittedDocs){
				document.save(converterUtils.addSubIndexBeforeExtension(outputUri, subIndex++));
			}
			
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	public void split(String inputUri, String outputUri, Integer pages) throws IOException, COSVisitorException {
		
		if(StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) &&
				pages != null){
			
			PDDocument doc = PDDocument.load(inputUri);
			
			Splitter splitter = new Splitter();
			
			splitter.setSplitAtPage(pages);
			
			List<PDDocument> splittedDocs = splitter.split(doc);
			
			Integer subIndex = 1;
			for(PDDocument document : splittedDocs){
				document.save(converterUtils.addSubIndexBeforeExtension(outputUri, subIndex++));
			}
			
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

	public void protect(String inputUri, String outputUri, String password) 
			throws IOException, BadSecurityHandlerException, COSVisitorException {
		
		if(StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) &&
				StringUtils.isNotBlank(password)){
		
		PDDocument doc = PDDocument.load(inputUri);
		
		StandardProtectionPolicy pp = new StandardProtectionPolicy(password, password, new AccessPermission());
		doc.protect(pp);
		
		doc.save(outputUri);
		
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}
	
	public void unProtect(String inputUri, String outputUri, String password) 
			throws IOException, COSVisitorException, BadSecurityHandlerException, CryptographyException{

		if(StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) &&
				StringUtils.isNotBlank(password)){
			
			PDDocument doc = PDDocument.load(inputUri);
			
			DecryptionMaterial decryptionMaterial = new StandardDecryptionMaterial(password);
	        doc.openProtection(decryptionMaterial);
	        
	        StandardProtectionPolicy pp = new StandardProtectionPolicy(null, null, new AccessPermission());
			doc.protect(pp);
			
			doc.save(outputUri);
			
		}else{
			throw new IllegalArgumentException(Constants.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
		}
	}

}
