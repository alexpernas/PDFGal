package org.pdfgal.pdfgal.model;

public interface PDFGalBookmark {

	Integer getPage();

	void setPage(Integer page);

	String getText();

	void setText(String text);

	boolean isInitializated();

}
