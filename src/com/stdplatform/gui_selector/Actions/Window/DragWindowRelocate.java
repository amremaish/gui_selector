package com.stdplatform.gui_selector.Actions.Window;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.component.XFrame;
import com.stdplatform.gui_selector.selector.Shape;
import com.stdplatform.gui_selector.util.util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


public class DragWindowRelocate implements DragEdgeListener, DragWindowListener {

	// drop Image
	private JLabel DropImage;
	private JPanel ImagePanel;
	// Palette
	private JWindow  PaletteFrame;
	private JPanel PalettePanel;
	private XFrame MainFrame;
	private Thread tempThread;

	// loaded Image
	private ImageLabelResized LoadedImage;
	private String ImagePath;

	// Shape List to Updated
	private ArrayList<Shape> shapeList;

	public void setDropImage(JLabel dragImage, JPanel DropImageParent) {
		this.DropImage = dragImage;
		this.ImagePanel = DropImageParent;
	}

	public void setPaletteFrame(JWindow  PaletteFrame, JPanel PalettePanel,
			XFrame MainFrame) {
		this.PaletteFrame = PaletteFrame;
		this.PalettePanel = PalettePanel;
		this.MainFrame = MainFrame;

	}

	public void UpdateAllAfter(final int millis) {
		tempThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(millis);
					UpdateAll();
					tempThread.join();
					tempThread = null;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		tempThread.start();
	}

	@Override
	public void WindowDragAction() {
		relocate();
	}

	@Override
	public void onWindowEdgeAction() {
		relocate();

	}

	public void setImage(ImageLabelResized Image, String path) {
		this.LoadedImage = Image;
		this.ImagePath = path;
	}

	public void UpdateAll() {
		relocate();
	}

	public void UpdateDropImage(int ImagePanelWidth, int ImagePanelHeight) {
		this.ImagePanel.setSize(ImagePanelWidth, ImagePanelHeight);
		relocate();

	}

	private void relocate() {
		// drop Image
		if (DropImage != null) {
			DropImage.setLocation(
					(ImagePanel.getWidth() >> 1) - (DropImage.getWidth() >> 1),
					(ImagePanel.getHeight() >> 1)
							- (DropImage.getHeight() >> 1));
			DropImage.repaint();
		}
		// relocate Palette
		if (PaletteFrame != null) {
			PaletteFrame.setLocation(MainFrame.getX() + 5, MainFrame.getY()
					+ MainFrame.getHeight() - PalettePanel.getHeight()
					- PaletteFrame.getHeight());
		}

		// image
		if (LoadedImage != null) {
			LoadedImage.setBounds(0, 0, ImagePanel.getWidth() + 10,
					ImagePanel.getHeight() + 10);
		}

		// update shapes bounds
		if (shapeList != null) {
			for (int i = 0; i < shapeList.size(); i++) {
				Shape thisShape = shapeList.get(i);
				// update X and Y
				double x = LoadedImage.getCalcX()+ (thisShape.getLastPX() * LoadedImage.getCalcWidth());
				double y = LoadedImage.getCalcY()+ (thisShape.getLastPY() * LoadedImage.getCalcHeight());
				thisShape.setLocation((int) Math.ceil(x), (int) Math.ceil(y));

				// Update Size
				double width = (thisShape.getLastPWidth() * LoadedImage
						.getCalcWidth());
				double height = (thisShape.getLastPHeight() * LoadedImage
						.getCalcHeight());
				thisShape.setSize((int) Math.ceil(width),(int) Math.ceil(height));

			}

		}
		
		System.gc();

	}

	public void setShapeList(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;
	}

}
