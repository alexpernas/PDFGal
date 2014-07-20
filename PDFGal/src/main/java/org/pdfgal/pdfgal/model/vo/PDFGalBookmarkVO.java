package org.pdfgal.pdfgal.model.vo;

import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgal.model.PDFGalBookmark;

public class PDFGalBookmarkVO implements PDFGalBookmark {

	private Integer page;

	private String text;

	public PDFGalBookmarkVO() {
		super();
	}

	public PDFGalBookmarkVO(final Integer page, final String text) {
		super();
		this.page = page;
		this.text = text;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public void setPage(final Integer page) {
		this.page = page;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public boolean isInitializated() {
		return (this.page != null) && StringUtils.isNotEmpty(this.text);
	}

}
