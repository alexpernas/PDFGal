package org.pdfgal.pdfgal.pdfgal.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.DecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.pdfgal.pdfgal.pdfgal.PDFGal;
import org.pdfgal.pdfgal.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class PDFGalImpl implements PDFGal {

	public void merge(List<String> inputUris, String outputUri) throws COSVisitorException, IOException {//TODO Crear excepcións propias
		
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

	public void split(String inputUri, String outputUri, List<Integer> pages) {
		// TODO Auto-generated method stub
		
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
