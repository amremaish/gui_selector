package com.stdplatform.gui_selector.component;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Frames.Palette;
import com.stdplatform.gui_selector.util.util;
import java.io.File;

public class itemPalette extends JPanel {

	private int id;
	private XLabel thisLabel;
	private String CompName;

	public itemPalette() {
		thisLabel = new XLabel();
		// initialize
		initialize();
		// Action
		Action();

	}

	private void initialize() {
		setLayout(null);
		this.add(thisLabel);

		setBackground(HoverColor.EXIT_PALLETE_ITEM);
	}

	private void Action() {
		thisLabel.setHoverAction(HoverColor.ENTER_PALLETE_ITEM,
				HoverColor.EXIT_PALLETE_ITEM);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHorizontalAlignment(int align) {
		thisLabel.setHorizontalAlignment(align);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		thisLabel.setSize(width, height);
	}

	public void setAbsoluteIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (width > 35 || height > 35) {
			if (width > height) {
				int div = width / height;
				width = 35;
				height = (int) Math.ceil(width / div);
			} else {
				int div = height / width;
				height = 35;
				width = (int) Math.ceil(height / div);
			}
		}
		image = util.resize(image, width, height);
		thisLabel.setIcon(new ImageIcon(image));
		thisLabel.setOpaque(true);

	}

	public void setIcon(String path) {
		ImageIcon icon = new ImageIcon(Palette.class.getClassLoader().getResource(path));
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		BufferedImage image = null;
		try {
			image = ImageIO.read(Palette.class.getClassLoader().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (width > 35 || height > 35) {
			if (width > height) {
				int div = width / height;
				width = 35;
				height = (int) Math.ceil(width / div);
			} else {
				int div = height / width;
				height = 35;
				width = (int) Math.ceil(height / div);
			}
		}
		image = util.resize(image, width, height);
		thisLabel.setIcon(new ImageIcon(image));
		thisLabel.setOpaque(true);

	}

	public XLabel getLabel() {
		return thisLabel;
	}

	public String getCompName() {
		return CompName;
	}

	public void setCompName(String compName) {
		CompName = compName;
	}




}
