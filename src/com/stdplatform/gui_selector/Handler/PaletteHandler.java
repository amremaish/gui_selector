package com.stdplatform.gui_selector.Handler;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Frames.Palette;
import com.stdplatform.gui_selector.Model.ItemModel;
import com.stdplatform.gui_selector.component.XTree;
import com.stdplatform.gui_selector.component.itemPalette;
import com.stdplatform.gui_selector.generate.AttributesGenerator;
import com.stdplatform.gui_selector.io.XML_component_loader;
import com.stdplatform.gui_selector.selector.Rectangle;
import com.stdplatform.gui_selector.selector.Shape;

public class PaletteHandler {

	private Palette PaletteDesign;
	private Main MainDesgin;
	private JPanel imagePanel;
	private MainHandler MainHandler;
	private XTree TreeItems;
	private ArrayList<Shape> shapeList;
	private HashMap<Integer, ItemModel> ItemModelIdMap;

	public PaletteHandler(Palette PaletteDesign, MainHandler MainHandler) {
		this.MainDesgin = MainHandler.getMainDesign();
		this.MainHandler = MainHandler;
		this.PaletteDesign = PaletteDesign;
		this.imagePanel = MainDesgin.getImagePanel();
		this.TreeItems = this.MainDesgin.getItemTree();
		ItemModelIdMap = MainHandler.getComponents().getItemModelIdMap();
		shapeList = MainHandler.getShapeList();
		initializeAction();
	}

	private void initializeAction() {
		ItemsAction();
		PrevNextButtonAction();
		// Jtree tools click Action
		JtreeToolClickAction();

	}

	private void ItemsAction() {
		if (PaletteDesign == null) {
			return;
		}
		ArrayList<JPanel> itemsPanelList = PaletteDesign.getItemsPanelList();
		for (int i = 0; i < itemsPanelList.size(); i++) {
			Component[] comList = itemsPanelList.get(i).getComponents();
			for (int j = 0; j < comList.length; j++) {
				final itemPalette thisItem = (itemPalette) comList[j];
				thisItem.getLabel().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						CreateShape(thisItem.getCompName());
					}
				});
			}
		}

	}

	private void PrevNextButtonAction() {
		if (PaletteDesign == null) {
			return;
		}

		PaletteDesign.getNextButton().setHoverAction(
				HoverColor.ENTER_PALLETE_ITEM, HoverColor.EXIT_PALLETE_ITEM);
		PaletteDesign.getPrevButton().setHoverAction(
				HoverColor.ENTER_PALLETE_ITEM, HoverColor.EXIT_PALLETE_ITEM);

		PaletteDesign.getNextButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaletteDesign.NextPanel();
			}
		});
		PaletteDesign.getPrevButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaletteDesign.PrevPanel();
			}
		});
	}

	private void JtreeToolClickAction() {
		MainDesgin.getRemove().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// remove from tree
				String selected = TreeItems.getSelectedItem();
				if (selected == null) {
					return;
				}
				int thisId = Integer.parseInt(selected.split("-")[1]);
				MainDesgin.getItemTree().deleteNode(thisId);

				// remove from image panel
				for (int i = 0; i < shapeList.size(); i++) {
					if (shapeList.get(i).getId() == thisId) {
						imagePanel.remove(shapeList.get(i));
						imagePanel.repaint();
						shapeList.remove(i);
						JPanel AttributesPanel = MainHandler.getMainDesign()
								.getAttributesPanel();
						Component[] compList = AttributesPanel.getComponents();
						for (int j = 0; j < compList.length; j++) {
							if (compList[j] instanceof Box) {
								MainHandler.getMainDesign()
										.getAttributesPanel()
										.remove(compList[j]);
							}
						}
						AttributesPanel.repaint();
						break;
					}
				}

			}
		});

		MainDesgin.getMoveUp().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = TreeItems.getSelectedItem();
				if (selected == null) {
					return;
				}
				int thisId = Integer.parseInt(selected.split("-")[1]);
				MainDesgin.getItemTree().MoveUp(thisId);
			}
		});
		MainDesgin.getMoveDown().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = TreeItems.getSelectedItem();
				if (selected == null) {
					return;
				}
				int thisId = Integer.parseInt(selected.split("-")[1]);
				MainDesgin.getItemTree().MoveDown(thisId);
			}
		});
	}

	public Rectangle CreateShape(String CompName) {
		ItemModel item = MainHandler.getComponents().getItemModelCompNameMap()
				.get(CompName);
		// assign info to new model
		ItemModel NewModel = new ItemModel();
		AttributesGenerator attGen = new AttributesGenerator(item);
		NewModel.setCompName(item.getCompName());
		NewModel.setSource(item.getSource());
		NewModel.setVBenchName(item.getVBenchName());
		NewModel.setAttributes(attGen.getAttList());
		NewModel.setBoxContainerList(attGen.getBoxList());
		// create rectangle
		Rectangle rect = new Rectangle(MainDesgin.getImage(), MainHandler, this);
		rect.setItemModel(NewModel);
		rect.setIconPath(NewModel.getSource());
		shapeList.add(rect);
		imagePanel.add(rect);
		imagePanel.setComponentZOrder(rect, 0);
		imagePanel.repaint();
		TreeItems.addNode(item.getCompName() + "-" + rect.getId());
		return rect;
	}


	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	public XTree getTreeItems() {
		return TreeItems;
	}

}
