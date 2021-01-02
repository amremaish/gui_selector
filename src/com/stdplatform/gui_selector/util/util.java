package com.stdplatform.gui_selector.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

public class util {

	private BufferedImage ColorIt(BufferedImage image, Color color) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dyed = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dyed;
	}

	public ImageIcon ChangeImageColor(String path, Color newColor) {

		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		image = ColorIt(image, newColor);
		return new ImageIcon(image);
	}

	public ImageIcon ChangeImageColor(ImageIcon ImageIco, Color newColor) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(ImageIco.toString().substring(5)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		image = ColorIt(image, newColor);
		return new ImageIcon(image);
	}
	public static  BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints((Map<?, ?>) new RenderingHints(
				RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	public static BufferedImage convertToAWT(ImageData data) {
	    ColorModel colorModel = null;
	    PaletteData palette = data.palette;
	    if (palette.isDirect) {
	        colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
	        BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                int pixel = data.getPixel(x, y);
	                RGB rgb = palette.getRGB(pixel);
	                bufferedImage.setRGB(x, y,  rgb.red << 16 | rgb.green << 8 | rgb.blue);
	            }
	        }
	        return bufferedImage;
	    } else {
	        RGB[] rgbs = palette.getRGBs();
	        byte[] red = new byte[rgbs.length];
	        byte[] green = new byte[rgbs.length];
	        byte[] blue = new byte[rgbs.length];
	        for (int i = 0; i < rgbs.length; i++) {
	            RGB rgb = rgbs[i];
	            red[i] = (byte)rgb.red;
	            green[i] = (byte)rgb.green;
	            blue[i] = (byte)rgb.blue;
	        }
	        if (data.transparentPixel != -1) {
	            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
	        } else {
	            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
	        }
	        BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
	        WritableRaster raster = bufferedImage.getRaster();
	        int[] pixelArray = new int[1];
	        for (int y = 0; y < data.height; y++) {
	            for (int x = 0; x < data.width; x++) {
	                int pixel = data.getPixel(x, y);
	                pixelArray[0] = pixel;
	                raster.setPixel(x, y, pixelArray);
	            }
	        }
	        return bufferedImage;
	    }
	}


}
