package com.stdplatform.gui_selector.util;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImageDrawer {

	private int CalcX, CalcY, CalcWidth, CalcHeight;
	private boolean atCenter;

	public void drawScaledImage(Image image, JPanel canvas, Graphics g) {
		int imgWidth = image.getWidth(null);
		int imgHeight = image.getHeight(null);

		double imgAspect = (double) imgHeight / imgWidth;

		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();

		double canvasAspect = (double) canvasHeight / canvasWidth;

		int x1 = 0; // top left X position
		int y1 = 0; // top left Y position
		int x2 = 0; // bottom right X position
		int y2 = 0; // bottom right Y position

		if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
			// the image is smaller than the canvas
			x1 = (canvasWidth - imgWidth) / 2;
			y1 = (canvasHeight - imgHeight) / 2;
			x2 = imgWidth + x1;
			y2 = imgHeight + y1;

		} else {
			if (canvasAspect > imgAspect) {
				y1 = canvasHeight;
				// keep image aspect ratio
				canvasHeight = (int) (canvasWidth * imgAspect);
				y1 = (y1 - canvasHeight) / 2;
			} else {
				x1 = canvasWidth;
				// keep image aspect ratio
				canvasWidth = (int) (canvasHeight / imgAspect);
				x1 = (x1 - canvasWidth) / 2;
			}
			x2 = canvasWidth + x1;
			y2 = canvasHeight + y1;
		}
		x1 += 10; // margin left
		y1 += 10;// margin left

		CalcX = x1;
		CalcY = y1;
		if (imgWidth < canvasWidth && imgHeight < canvasWidth) {
			CalcWidth = imgWidth;
			CalcHeight = imgHeight;
		} else {
			CalcWidth = canvasWidth;
			CalcHeight = canvasHeight;
		}
		if (atCenter) {
			g.drawImage(image, x1 - 4, y1 - 4, x2 - 4, y2 - 4, 0, 0, imgWidth, imgHeight, null);
		} else {
			g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
		}

	}

	public int getCalcX() {
		return CalcX;
	}

	public int getCalcY() {
		return CalcY;
	}

	public int getCalcWidth() {
		return CalcWidth;
	}

	public int getCalcHeight() {
		return CalcHeight;
	}

	public void setAtCenter() {
		atCenter = true;
	}
}