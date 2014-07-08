package org.pdfgal.pdfgal.model.enumerated;

public enum WatermarkPosition {

	CENTER(0D, 60D, 400D, 12), GOING_DOWN(-1D, 50D, 750D, 21), GOING_UP(1D, 100D, 60D, 21);

	private Double rotationAngle;
	private Double rotationTX;
	private Double rotationTY;
	private Integer maxLength;

	/**
	 * @param rotationAngle
	 * @param rotationTX
	 * @param rotationTY
	 * @param maxLength
	 */
	private WatermarkPosition(final Double rotationAngle, final Double rotationTX,
			final Double rotationTY, final Integer maxLength) {
		this.rotationAngle = rotationAngle;
		this.rotationTX = rotationTX;
		this.rotationTY = rotationTY;
		this.maxLength = maxLength;
	}

	/**
	 * @return the rotationAngle
	 */
	public Double getRotationAngle() {
		return this.rotationAngle;
	}

	/**
	 * @param rotationAngle the rotationAngle to set
	 */
	public void setRotationAngle(final Double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	/**
	 * @return the rotationTX
	 */
	public Double getRotationTX() {
		return this.rotationTX;
	}

	/**
	 * @param rotationTX the rotationTX to set
	 */
	public void setRotationTX(final Double rotationTX) {
		this.rotationTX = rotationTX;
	}

	/**
	 * @return the rotationTY
	 */
	public Double getRotationTY() {
		return this.rotationTY;
	}

	/**
	 * @param rotationTY the rotationTY to set
	 */
	public void setRotationTY(final Double rotationTY) {
		this.rotationTY = rotationTY;
	}

	/**
	 * @return the maxLength
	 */
	public Integer getMaxLength() {
		return this.maxLength;
	}

	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(final Integer maxLength) {
		this.maxLength = maxLength;
	}
}
