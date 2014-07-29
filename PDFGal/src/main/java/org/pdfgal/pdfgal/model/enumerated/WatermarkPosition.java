/*
 * PDFGal
 * Copyright (c) 2014, Alejandro Pernas Pan, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package org.pdfgal.pdfgal.model.enumerated;

public enum WatermarkPosition {

	CENTER(0D, 60D, 400D, 12, 0D, 50D, 275D, 18),

	BOTTOM_UP(1D, 100D, 60D, 21, 0.58D, 80D, 50D, 21),

	TOP_DOWN(-1D, 50D, 750D, 21, -0.55D, 50D, 490D, 21);

	private Double rotationAnglePortrait;
	private Double rotationTXPortrait;
	private Double rotationTYPortrait;
	private Integer maxLengthPortrait;
	private Double rotationAngleLandscape;
	private Double rotationTXLandscape;
	private Double rotationTYLandscape;
	private Integer maxLengthLandscape;

	private WatermarkPosition(final Double rotationAnglePortrait, final Double rotationTXPortrait,
			final Double rotationTYPortrait, final Integer maxLengthPortrait,
			final Double rotationAngleLandscape, final Double rotationTXLandscape,
			final Double rotationTYLandscape, final Integer maxLengthLandscape) {
		this.rotationAnglePortrait = rotationAnglePortrait;
		this.rotationTXPortrait = rotationTXPortrait;
		this.rotationTYPortrait = rotationTYPortrait;
		this.maxLengthPortrait = maxLengthPortrait;
		this.rotationAngleLandscape = rotationAngleLandscape;
		this.rotationTXLandscape = rotationTXLandscape;
		this.rotationTYLandscape = rotationTYLandscape;
		this.maxLengthLandscape = maxLengthLandscape;
	}

	public Double getRotationAnglePortrait() {
		return this.rotationAnglePortrait;
	}

	public void setRotationAnglePortrait(final Double rotationAnglePortrait) {
		this.rotationAnglePortrait = rotationAnglePortrait;
	}

	public Double getRotationTXPortrait() {
		return this.rotationTXPortrait;
	}

	public void setRotationTXPortrait(final Double rotationTXPortrait) {
		this.rotationTXPortrait = rotationTXPortrait;
	}

	public Double getRotationTYPortrait() {
		return this.rotationTYPortrait;
	}

	public void setRotationTYPortrait(final Double rotationTYPortrait) {
		this.rotationTYPortrait = rotationTYPortrait;
	}

	public Integer getMaxLengthPortrait() {
		return this.maxLengthPortrait;
	}

	public void setMaxLengthPortrait(final Integer maxLengthPortrait) {
		this.maxLengthPortrait = maxLengthPortrait;
	}

	public Double getRotationAngleLandscape() {
		return this.rotationAngleLandscape;
	}

	public void setRotationAngleLandscape(final Double rotationAngleLandscape) {
		this.rotationAngleLandscape = rotationAngleLandscape;
	}

	public Double getRotationTXLandscape() {
		return this.rotationTXLandscape;
	}

	public void setRotationTXLandscape(final Double rotationTXLandscape) {
		this.rotationTXLandscape = rotationTXLandscape;
	}

	public Double getRotationTYLandscape() {
		return this.rotationTYLandscape;
	}

	public void setRotationTYLandscape(final Double rotationTYLandscape) {
		this.rotationTYLandscape = rotationTYLandscape;
	}

	public Integer getMaxLengthLandscape() {
		return this.maxLengthLandscape;
	}

	public void setMaxLengthLandscape(final Integer maxLengthLandscape) {
		this.maxLengthLandscape = maxLengthLandscape;
	}

}
