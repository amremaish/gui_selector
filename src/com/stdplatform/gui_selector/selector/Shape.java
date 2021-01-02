package com.stdplatform.gui_selector.selector;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stdplatform.gui_selector.Actions.Component.item_palette.DragItemListener;
import com.stdplatform.gui_selector.Actions.Component.item_palette.Resizable;
import com.stdplatform.gui_selector.Actions.Window.DragWindowRelocate;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Frames.Palette;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Handler.PaletteHandler;
import com.stdplatform.gui_selector.Model.ItemModel;
import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.component.XPanel;
import com.stdplatform.gui_selector.io.XML_component_loader;

public class Shape extends XPanel implements DragItemListener {

	// constant names
	public static final String KEY_NAME = "KEY";
	public static final String KNOB_NAME = "KNOB";
	public static final String CAP_TOUCH_NAME = "CAP_TOUCH";
	public static final String LCD_NAME = "LCD";
	public static final String LED_NAME = "LED";
	/*
	 *
	 */
	protected Image icon;
	protected int iconWidth, iconHeight;
	private ItemModel itemModel  ;
	private JPanel Parent;
	private ImageLabelResized LoadedImage;
	private MainHandler mainHandler;
	private PaletteHandler paletteHandler;
	private boolean selected;
	private double lastPX, LastPY, LastPWidth, LastPHeight;

	public Shape(ImageLabelResized LoadedImage, MainHandler mainHandler,
			PaletteHandler paletteHandler) {
		this.mainHandler = mainHandler;
		this.LoadedImage = LoadedImage;
		this.paletteHandler = paletteHandler;
		inialize();
		action();
	}

	private void inialize() {
		setOpaque(false);
		this.setBounds(LoadedImage.getCalcX(), LoadedImage.getCalcY(), 100, 100);
	}

	// Button_Left
	public void setIconPath(String Path) {
		ImageIcon imgIcon = new ImageIcon(Path);
		this.iconWidth = imgIcon.getIconWidth();
		this.iconHeight = imgIcon.getIconHeight();
		icon = imgIcon.getImage();
	}

	private void action() {
		Resizable res = new Resizable(this);
		res.setDragItemListener(this);

		// request facus & Properties
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ShapePressAction();
			}

		});

		final Shape thisShape = this;
		// delete key
		addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
					// remove from tree
					String selected = paletteHandler.getTreeItems()
							.getSelectedItem();
					if (selected == null) {
						return;
					}
					int thisId = Integer.parseInt(selected.split("-")[1]);
					mainHandler.getMainDesign().getItemTree()
							.deleteNode(thisId);
					// -------------------
					// remove from image panel
					JPanel imagePanel = mainHandler.getMainDesign()
							.getImagePanel();
					ArrayList<Shape> shapeList = mainHandler.getShapeList();
					imagePanel.remove(thisShape);
					imagePanel.repaint();
					shapeList.remove(thisShape);
					// remove All attribute
					JPanel AttributesPanel = mainHandler.getMainDesign().getAttributesPanel();
					// remove All Boxes
					mainHandler.getMainDesign().ClearAttributesBoxes();
					// resize scroll pane
					mainHandler.getMainDesign().setHeightOfScroll(100);
					AttributesPanel.repaint();
					AttributesPanel.revalidate();
				}
			}
		});
	}

	public void ShapePressAction() {
		// focus action
		this.requestFocus();
		this.setFocusable(true);
		this.requestFocusInWindow();
		updateDesign();
		selected = true;
		// set other selected false
		for (int i = 0; i < mainHandler.getShapeList().size(); i++) {
			int id = mainHandler.getShapeList().get(i).getId();
			if (super.getId() != id ) {
				mainHandler.getShapeList().get(i).repaint();
				mainHandler.getShapeList().get(i).setSelected(false);
			}
		}
		mainHandler.getMainDesign().getImagePanel().repaint();
		this.repaint();
	}

	private void updateDesign() {
		ArrayList<Box> boxList = itemModel.getBoxContainerList();
		JPanel AttributesPanel = mainHandler.getMainDesign().getAttributesPanel();
		// remove All Boxes
		mainHandler.getMainDesign().ClearAttributesBoxes();
		// ----------------------------------------------------------------
		// add new Box
		for (int i = 0; i < boxList.size(); i++) {
			AttributesPanel.add(boxList.get(i));
		}
		AttributesPanel.repaint();
		AttributesPanel.revalidate();
		// set Scroll resize
		if (boxList.size() != 0 ){
		int size  = boxList.get(0).getPreferredSize().height * (boxList.size()+2) ;
			mainHandler.getMainDesign().setHeightOfScroll(size);
		}
	}

	protected void SharedGraphcis(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// enable antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// opacity
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.7f));

		g.setColor(Color.decode("#000000"));

		// focus action
		Parent = (JPanel) this.getParent();
		Parent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				Parent.requestFocus();
				// remove All Boxes
				mainHandler.getMainDesign().ClearAttributesBoxes();
				// resize scroll pane
				mainHandler.getMainDesign().setHeightOfScroll(100);

				mainHandler.getMainDesign().getCompLblName().setVisible(false);
				selected = false;
				repaint();
			}
		});

	}

	@Override
	public void DragItemAction() {

		lastPX = (double) (this.getX() - LoadedImage.getCalcX())
				/ (double) LoadedImage.getCalcWidth();
		LastPY = (double) (this.getY() - LoadedImage.getCalcY())
				/ (double) LoadedImage.getCalcHeight();

		LastPWidth = (double) this.getWidth()
				/ (double) LoadedImage.getCalcWidth();

		LastPHeight = (double) this.getHeight()
				/ (double) LoadedImage.getCalcHeight();

	}

	public double getLastPWidth() {
		return LastPWidth;
	}

	public double getLastPHeight() {
		return LastPHeight;
	}

	public double getLastPX() {
		return lastPX;
	}

	public double getLastPY() {
		return LastPY;
	}


	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public JPanel getSuperPanel() {
		return this;
	}

	public ImageLabelResized getLoadedImage() {
		return LoadedImage;
	}

	public void setLoadedImage(ImageLabelResized loadedImage) {
		LoadedImage = loadedImage;
	}

	public void setLastPX(double lastPX) {
		this.lastPX = lastPX;
	}

	public void setLastPY(double lastPY) {
		LastPY = lastPY;
	}

	public void setLastPWidth(double lastPWidth) {
		LastPWidth = lastPWidth;
	}

	public void setLastPHeight(double lastPHeight) {
		LastPHeight = lastPHeight;
	}


	public ItemModel getItemModel() {
		return itemModel;
	}

	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
	}


}
