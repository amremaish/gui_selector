package com.stdplatform.gui_selector.Frames;

import javax.swing.JWindow;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.stdplatform.gui_selector.component.XLabel;
import com.stdplatform.gui_selector.component.itemPalette;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Handler.PaletteHandler;
import com.stdplatform.gui_selector.Model.ItemModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import java.awt.Font;

public class Palette {

	public static final int WINDOW_WIDTH = 350, WINDOW_HEIGHT = 140;
	public static final int ITEM_WIDTH = 40, ITEM_HEIGHT = 40;
	public static final int ITEM_GAP = 20;
	private final Dimension StartDim = new Dimension(25, 20);

	private JWindow frame;
	private JPanel itemPanel;
	private JPanel MainPanel;
	private XLabel PrevButton, NextButton;
	private int panel_idx;
	private ArrayList<JPanel> itemsPanelList;
	private ArrayList<ItemModel> itemModelList;

	private MainHandler mainHandler;

	private JPanel MainItemPanel;

	public Palette(MainHandler mainHandler) {
		this.mainHandler = mainHandler;
		itemModelList = mainHandler.getComponents().getItemModelList();
		itemsPanelList = new ArrayList<JPanel>();
		initialize();
		buildItems();
		new PaletteHandler(this, mainHandler);
	}


	private void initialize() {
		frame = new JWindow(SwingUtilities.windowForComponent(mainHandler
				.getMainDesign().getMainFrame()));

		frame.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		MainPanel = new JPanel();
		MainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));

		MainItemPanel = new JPanel();
		MainPanel.add(MainItemPanel, BorderLayout.CENTER);
		MainItemPanel.setLayout(new BorderLayout(0, 0));

		// -----------------------------------------------------------
		PrevButton = new XLabel("<");
		PrevButton.setBackground(Color.LIGHT_GRAY);
		PrevButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		PrevButton.setHorizontalAlignment(SwingConstants.CENTER);
		PrevButton.setPreferredSize(new Dimension(40, 23));
		PrevButton.setMinimumSize(new Dimension(40, 23));
		PrevButton.setMaximumSize(new Dimension(40, 23));
		MainPanel.add(PrevButton, BorderLayout.WEST);

		NextButton = new XLabel(">");
		NextButton.setBackground(Color.LIGHT_GRAY);
		NextButton.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		NextButton.setHorizontalAlignment(SwingConstants.CENTER);
		NextButton.setPreferredSize(new Dimension(40, 23));
		NextButton.setMinimumSize(new Dimension(40, 23));
		NextButton.setMaximumSize(new Dimension(40, 23));
		MainPanel.add(NextButton, BorderLayout.EAST);
	}

	private void buildItems() {
		JPanel curItemPanel = null;
		int curX = StartDim.width;
		int curY = StartDim.height;
		int countItem = 0;
		for (int i = 0; i < itemModelList.size(); i++) {
			if (countItem % 8 == 0) {
				curItemPanel = new JPanel();
				curItemPanel.setBorder(new MatteBorder(1, 1, 1, 1,
						(Color) Color.BLACK));
				curItemPanel.setLayout(null);
				itemsPanelList.add(curItemPanel);
				curX = StartDim.width;
				curY = StartDim.height;
				countItem = 0 ;
			}
			if (countItem == 4) {
				curY += ITEM_GAP + ITEM_HEIGHT;
				curX = StartDim.width;
			}
			itemPalette item = new itemPalette();
			item.setId(itemModelList.get(i).getId());
			item.setCompName(itemModelList.get(i).getCompName());

			item.setAbsoluteIcon(itemModelList.get(i).getSource()); // will change

			item.setBounds(curX, curY, ITEM_WIDTH, ITEM_HEIGHT);
			item.setHorizontalAlignment(SwingConstants.CENTER);
			curItemPanel.add(item);
			countItem++;
			curX += ITEM_GAP + ITEM_WIDTH;
		}

		MainItemPanel.add(itemsPanelList.get(0), BorderLayout.CENTER);

	}

	public void NextPanel() {
		if ( (panel_idx + 1) < (itemModelList.size() / 8) + 1 ) {
			panel_idx++;
			System.out.println(panel_idx);
			MainItemPanel.removeAll();
			MainItemPanel.add(itemsPanelList.get(panel_idx), BorderLayout.CENTER);
			MainItemPanel.revalidate();
			MainItemPanel.repaint();
		}
	}

	public void PrevPanel() {
		if ((panel_idx - 1) >= 0) {
			panel_idx--;
			System.out.println(panel_idx);
			MainItemPanel.removeAll();
			MainItemPanel.add(itemsPanelList.get(panel_idx), BorderLayout.CENTER);
			MainItemPanel.repaint();
			MainItemPanel.revalidate();

		}
	}


	public void close() {
		frame.setVisible(false);
		frame.dispose();
		frame = null;
	}

	public JWindow getFramePalette() {
		return frame;
	}

	public Component[] getItemList() {
		return itemsPanelList.get(0).getComponents();
	}

	public JPanel getMainPanel() {
		return itemPanel;
	}

	public JWindow getFrame() {
		return frame;
	}

	public XLabel getPrevButton() {
		return PrevButton;
	}

	public void setPrevButton(XLabel prevButton) {
		PrevButton = prevButton;
	}

	public XLabel getNextButton() {
		return NextButton;
	}

	public void setNextButton(XLabel nextButton) {
		NextButton = nextButton;
	}

	public ArrayList<JPanel> getItemsPanelList() {
		return itemsPanelList;
	}

}
