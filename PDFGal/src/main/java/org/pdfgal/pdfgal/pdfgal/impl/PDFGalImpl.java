package org.pdfgal.pdfgal.pdfgal.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.pdfgal.pdfgal.pdfgal.PDFGal;

public class PDFGalImpl implements PDFGal {

	@Override
	public void merge(List<String> inputUris, String outputUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void split(String inputUri, String outputUri, List<Integer> pages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void protect(String inputUri, String outputUri, String password) throws IOException, BadSecurityHandlerException, COSVisitorException {
		
		if(StringUtils.isNotBlank(inputUri) && StringUtils.isNotBlank(outputUri) &&
				StringUtils.isNotBlank(password)){
		
		PDDocument doc = PDDocument.load(inputUri);
		
		StandardProtectionPolicy pp = new StandardProtectionPolicy(password, password, new AccessPermission());
		doc.protect(pp);
		
		doc.save(outputUri);
		
		}
	}
	
	@Override
	public void unProtect(String inputUri, String outputUri, String password){
		//TODO
	}

}
